import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { AbstractBaseComponent } from "./abstract.base.component";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { ViewContainerRef } from "@angular/core";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import {LoadingIndicator} from "nativescript-loading-indicator";

import { PagingAndFilterRequest } from "../../services/authorization/pagingandfilterrequest.model";
import { FilterCriteria } from "../../services/authorization/filtercriteria.model";
import { PagingAndFilterResponse } from "../../services/authorization/pagingandfilterresponse.model";
import { AuthService } from "../../services/auth.service";

import { Page } from "tns-core-modules/ui/page";
import { SearchBar } from "tns-core-modules/ui/search-bar";

import * as _ from "lodash";


export abstract class BaseComponent extends AbstractBaseComponent implements OnInit {
    loadingIndicator: LoadingIndicator;
    loadingIndicatorOptions: any;
    constructor(service: any, authService: AuthService, 
            private modalDialogService?: ModalDialogService, private dialogService?: any, private detailComponent?: any, 
            private router?: Router, private activatedRoute?: ActivatedRoute, private vcRef?: ViewContainerRef) {
        super(service, authService);
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
    protected onSearch(args) {
        console.log("onSearch");
        const searchBar: SearchBar = <SearchBar>args.object;
        const searchValue = searchBar.text.toLowerCase();
        const arrayItems = [
                            { name: "United States" },
                            { name: "Bulgaria" },
                            { name: "Germany" },
                            { name: "Denmark" },
                            { name: "India" },
                            { name: "Spain" },
                            { name: "Italy" }
                        ];
        const myItems = new ObservableArray();
        if (searchValue !== "") {
            for (let i = 0; i < arrayItems.length; i++) {
                if (arrayItems[i].name.toLowerCase().indexOf(searchValue) !== -1) {
                    myItems.push(arrayItems[i]);
                }
            }
        }
        const page: Page = <Page>searchBar.page;
        const vm = page.bindingContext;
        vm.set("myItems", myItems);
    }
    public onClear(args) {
        
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
    totalNumberOfPages: number;
    previousPageNumber: number;
    currentPageNumber: number = 1;
    nextPageNumber: number;
    lastPageNumber: number;
    pageNumbers: number[] = [1,2,3,4];
    maxSize: number = 4;
    gotoPage(pageNumber: number) {
        console.log(pageNumber);
        this.currentPageNumber = pageNumber;
        this.lazyLoadRecordList({"first": (pageNumber - 1) * this.numberOfRowsPerPage , "rows": this.numberOfRowsPerPage});
    }
    protected recordListLoaded(): void {
        this.totalNumberOfPages = this.totalRecords / this.numberOfRowsPerPage;
        this.totalNumberOfPages = Math.ceil(this.totalNumberOfPages);
        if (this.totalNumberOfPages > 1) {
            let pageNumbers = [];
            for (var index = this.currentStartPageNo; index < this.currentStartPageNo + this.maxSize && index <= this.totalNumberOfPages; index++) {
                pageNumbers.push(index);
            }
            this.pageNumbers = pageNumbers;
        } else {
            this.pageNumbers = [1];
        }
    }
    
    get currentStartPageNo():number {
        if ((this.currentPageNumber - Math.ceil((this.maxSize/2)) <= 0)) {
            return 1;
        }
        else
        {
            if (this.totalNumberOfPages >= (this.currentPageNumber + Math.ceil((this.maxSize/2))) + 1) {
                return (this.currentPageNumber - Math.ceil((this.maxSize/2)) + 1);
            }
            else if ((this.currentPageNumber - this.maxSize) <= 0) {
                return 1;
            } else {
                return this.totalNumberOfPages - (this.maxSize - 1);
            }
        }
    }
}
