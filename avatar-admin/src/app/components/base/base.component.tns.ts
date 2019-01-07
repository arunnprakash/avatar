import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { AbstractBaseComponent } from "./abstract.base.component";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import {LoadingIndicator} from "nativescript-loading-indicator";

import { PagingAndFilterRequest } from "../../services/authorization/pagingandfilterrequest.model";
import { FilterCriteria } from "../../services/authorization/filtercriteria.model";
import { PagingAndFilterResponse } from "../../services/authorization/pagingandfilterresponse.model";
import * as _ from "lodash";
import { ViewContainerRef } from "@angular/core";

export abstract class BaseComponent extends AbstractBaseComponent implements OnInit {
    loadingIndicator: LoadingIndicator;
    loadingIndicatorOptions: any;
    constructor(service: any, private modalDialogService?: ModalDialogService, private dialogService?: any, private detailComponent?: any, 
            private router?: Router, private activatedRoute?: ActivatedRoute, private vcRef?: ViewContainerRef) {
        super(service);
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit base.component.tns");
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

        this.lazyLoadRecordList();
    }
    protected showDetailDialog(value: boolean) {
        const options: any = {
                viewContainerRef: this.vcRef,
                context: {model: this.model, cols: this.cols, localCols: this.localCols, title: this.title, displayEditDetail: this.model.id?false:true },
                fullscreen: false,
        };
        this.modalDialogService.showModal(this.detailComponent, options);
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
    protected onDelete() {
        const confirmOptions: ConfirmOptions = {
                title: "Delete "+this.title+"s",
                message: "Are you sure that you want to Delete?",
                okButtonText: "Ok",
                cancelButtonText: "Cancel"
        };
        confirm(confirmOptions)
                .then((confirmed) => {
                    if (confirmed) {
                        this.delete();
                    }
        });
    }

}
