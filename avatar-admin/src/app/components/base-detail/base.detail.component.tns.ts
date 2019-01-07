import { OnInit, Input } from '@angular/core';
import { AbstractBaseDetailComponent } from "./abstract.base.detail.component";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { ModalDialogService, ModalDialogOptions, ModalDialogParams } from "nativescript-angular/modal-dialog";
import { isAndroid } from "platform";

import {LoadingIndicator} from "nativescript-loading-indicator";

import * as _ from "lodash";

export abstract class BaseDetailComponent extends AbstractBaseDetailComponent  implements OnInit {

    loadingIndicator: LoadingIndicator;
    loadingIndicatorOptions: any;
    constructor(service: any, private params?: any, private dialog?: any) {
        super(service);
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
        if (isAndroid) {
            event.object._dialogFragment.getDialog().setCanceledOnTouchOutside(false);
        }
    }

    protected closeDetailDialog() {
        this.params.closeCallback();
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
}
