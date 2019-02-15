import { OnInit, Input } from '@angular/core';
import { AbstractBaseDetailComponent } from "./abstract.base.detail.component";
import { AuthService } from "../../services/auth.service";

import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { ModalDialogService, ModalDialogOptions, ModalDialogParams } from "nativescript-angular/modal-dialog";
import { isAndroid } from "tns-core-modules/platform";

import * as imagepicker from "nativescript-imagepicker";
import { LoadingIndicator } from "nativescript-loading-indicator";
import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";
import { ImageSource, fromFile } from "tns-core-modules/image-source";

import * as _ from "lodash";

export abstract class BaseDetailComponent extends AbstractBaseDetailComponent  implements OnInit {

    loadingIndicator: LoadingIndicator;
    loadingIndicatorOptions: any;
    constructor(service: any, authService: AuthService, private params?: any, private dialog?: any) {
        super(service, authService);
        this.model = params.context.model;
        this.cols = params.context.cols;
        this.localCols = params.context.localCols;
        this.title = params.context.title;
        this.displayEditDetail = params.context.displayEditDetail;
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit Base Detail Component.tns");
        this.loadingIndicator = new LoadingIndicator();
        this.loadingIndicatorOptions = {
                message: 'Loading...',
                progress: 0.65,
                android: {
                  indeterminate: true,
                  cancelable: true,
                  cancelListener: function(dialog) { console.log("Loading cancelled") },
                  max: 100,
                  progressNumberFormat: "%1d/%2d",
                  progressPercentFormat: 0.53,
                  progressStyle: 1,
                  secondaryProgress: 1
                }
              };
    }
    onLoaded(event) {
        if (isAndroid && event.object._dialogFragment) {
            event.object._dialogFragment.getDialog().setCanceledOnTouchOutside(false);
        }
    }
    onAutoCompleteLoaded(event, readOnly, model, col) {
        var optionLabel = col.optionLabel;
        let autoComplete: RadAutoCompleteTextView = <RadAutoCompleteTextView>event.object;
        autoComplete.readOnly = readOnly;
        if (model) {
           if ( _.isArray(model) ) {
               if (readOnly) {
                   var tokenText = "";
                   model.forEach((modelItem:any, index)=>{
                       tokenText = index == 0 ? tokenText + modelItem[optionLabel] : tokenText + "," + modelItem[optionLabel];
                   });
                   autoComplete.addToken(new TokenModel(tokenText, null));
               } else {
                   model.forEach((modelItem:any, index)=>{
                       autoComplete.addToken(new TokenModel(modelItem[optionLabel], null));
                   });
               }
           } else {
               autoComplete.addToken(new TokenModel(model[optionLabel], null));
           }
        }
        if (readOnly) {
            if (isAndroid) {
                var rad = autoComplete.android;
                var nativeEditText = rad.getTextField();
                nativeEditText.setTextSize(14);
                //nativeEditText.setTextColor(32768);//0x00008000
            }
        }
    }
    onTokenAdded(event, col) {
        var tokenText = event.token.text;
        var optionLabel = col.optionLabel;
        var searchOptions = {};
        searchOptions[optionLabel] = tokenText;
        var selectedOption = _.find(col.originalOptions, searchOptions);
        if (col.multiple) {
            var existingOptions = this.model[col.field];
            if (!existingOptions) {
                existingOptions = [];
            }
            var optionExist = _.find(existingOptions, {'id': selectedOption.id});
            if (!optionExist) {
                existingOptions.push(selectedOption);
            }
            this.model[col.field] = existingOptions;
        } else {
            this.model[col.field] = selectedOption;
        }
    }
    onTokenRemoved(event, col) {
        var tokenText = event.token.text;
        var optionLabel = col.optionLabel;
        var searchOptions = {};
        searchOptions[optionLabel] = tokenText;
        if (col.multiple) {
            var deletedOption = _.find(col.originalOptions, searchOptions);
            var existingOptions = this.model[col.field];
            if (!existingOptions) {
                existingOptions = [];
            }
            var option = _.find(existingOptions, {'id': deletedOption.id});
            if (option) {
                existingOptions = _.remove(existingOptions, {'id': option.id});
            }
            this.model[col.field] = existingOptions;
        } else {
            this.model[col.field] = null;
        }
    }
    protected closeDetailDialog() {
        this.params.closeCallback(this.saved);
    }
    protected showLoading(value: boolean) {
        if (value) {
            this.loadingIndicator.show(this.loadingIndicatorOptions);
        } else {
            this.loadingIndicator.hide();
        }
    }
    protected showAlertDialog(title: string, message: string) {
        const alertOptions: AlertOptions = {
            title: title,
            message: message,
            okButtonText: "OK",
            cancelable: false // [Android only] Gets or sets if the dialog can be canceled by taping outside of the dialog.
        };

        alert(alertOptions).then(() => {
            console.log("Dialog closed!");
        });
    }
    assetExist(field, assetType) {
        if (this.model[field]) {
            var assets = this.model[field];
            var asset = _.find(assets, function(asset) { return asset.assetType.assetTypeName == assetType.assetTypeName; });
            return asset?true:false;
        }
        return false;
    }
    fileSelectedEventHandler(field, assetType) {
        console.log("assetType {}", assetType);
        let context = imagepicker.create({
            mode: "single" // use "multiple" for multiple selection
        });
        context
        .authorize()
        .then(function() {
            return context.present();
        })
        .then( (selected: any[]) => {
                // process the selected image
                let selectedImage: any = selected[0];
                const img: ImageSource = <ImageSource>fromFile(selectedImage._android);
                const base64String = img.toBase64String("jpg");
                if (!this.model[field]) {
                    this.model[field] = [];
                }
                var assets = this.model[field];
                var asset = _.find(assets, function(asset) { return asset.assetType.assetTypeName == assetType.assetTypeName; });
                if (!asset) {
                    asset = {};
                    this.model[field].push(asset);
                }
                asset['assetValue'] = "data:image/jpeg;base64," + base64String;
                asset['assetType'] = assetType;
        }).catch(function (e) {
            // process error
            console.log("Error while processing image", e);
        });
    }
    protected onSelectAutoComplete(field) {
        //Do nothing default implementation
    }
}
