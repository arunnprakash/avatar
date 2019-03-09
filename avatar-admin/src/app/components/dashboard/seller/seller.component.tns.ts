import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { Page } from "tns-core-modules/ui/page";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { LoadingIndicator } from "nativescript-loading-indicator";
import { fromBase64, fromResource }  from "tns-core-modules/image-source";
import { Router, ActivatedRoute } from "@angular/router";
import { ViewContainerRef } from "@angular/core";
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "../../../services/auth.service";
import { UserDTO } from "../../../services/authorization/userdto.model";
import { QualityDTO } from "../../../services/product/qualitydto.model";
import { QualityService } from '../../../services/product/qualityservice.generated';
import { PagingAndFilterRequest } from "../../../services/product/pagingandfilterrequest.model";
import { FilterCriteria } from "../../../services/product/filtercriteria.model";
import { PagingAndFilterResponse } from "../../../services/product/pagingandfilterresponse.model";
import { PriceHistoryService } from '../../../services/product/pricehistoryservice.generated';

import { SellProductComponent } from "./sell-product/sell-product.component";
import { ObservableArray } from "tns-core-modules/data/observable-array";
import * as _ from "lodash";

@Component( {
    selector: 'seller',
    templateUrl: './seller.component.html',
    styleUrls: ['./seller.component.css'],
    providers: [ModalDialogService]
} )
export class SellerComponent implements OnInit {
    public sideDrawerTransition: DrawerTransitionBase;
    private drawer: RadSideDrawer;
    private loadingIndicator: LoadingIndicator;
    private loadingIndicatorOptions: any;
    protected numberOfRowsPerPage = 8;
    protected priceList: any[];
    protected totalRecords: number;
    protected languageCode: string;
    protected userDTO: UserDTO;
    protected qualities: QualityDTO[];
    constructor(private authService: AuthService, private priceHistoryService: PriceHistoryService,
            private qualityService: QualityService,
            private translate: TranslateService, 
            private router: Router, private activatedRoute: ActivatedRoute, 
            private vcRef: ViewContainerRef, private dialogService: ModalDialogService) { 
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.info( "On Init Seller Component" );
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
        this.initQualityList();
        this.lazyLoadRecordList();
    }
    initQualityList() {
        this.qualityService.getAllExceptDeleted().subscribe((qualities: QualityDTO[]) => {
            this.qualities = qualities;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Quality List');
        });
    }
    lazyLoadRecordList(event?: any) {
        // in a real application, make a remote request to load data using state metadata from event
        // event.first = First row offset
        // event.rows = Number of rows per page
        // event.sortField = Field name to sort with
        // event.sortOrder = Sort order as number, 1 for asc and -1 for dec
        // filters: FilterMetadata object having field as key and filter value, filter matchMode as value
        // this.loading = true;
        let filters: FilterCriteria[] = [];
        if (event && event.filters) {
            for (const key in event.filters) {
                if (key != null) {
                    let filterCriteria: FilterCriteria = new FilterCriteria();
                    const filterMetadata: any = event.filters[key];
                    console.info(key, event.filters[key]);
                    const keyValues = event.filters[key].value;
                    filterCriteria.filterByItem = key;
                    filterCriteria.filterByItemValues =  _.isArray(keyValues) ? keyValues : [keyValues];
                    filters.push(filterCriteria);
                }
            }
        }
        let pageNumber = event && event.first ? event.first / event.rows : 0;
        let pageSize = event && event.rows ? event.rows:this.numberOfRowsPerPage;
        let sortBy = event && event.sortField ? event.sortField:'id';
        let sortingOrder = event &&  event.sortOrder === 1 ? 'ASC' : 'DESC';
        let pagingAndFilterRequest: PagingAndFilterRequest = new PagingAndFilterRequest();
        pagingAndFilterRequest.filters = filters;
        pagingAndFilterRequest.pageNumber = pageNumber;
        pagingAndFilterRequest.pageSize = pageSize;
        pagingAndFilterRequest.sortBy = sortBy;
        pagingAndFilterRequest.sortingOrder = sortingOrder;
        this.loadRecordList(pagingAndFilterRequest);
    }
    filter(value: any, fieldName: string) {
        let filters: FilterCriteria[] = [];
        if (value !== "") {
            let filterCriteria: FilterCriteria = new FilterCriteria();
            filterCriteria.filterByItem = fieldName;
            filterCriteria.filterByItemValues = [value];
            filters.push(filterCriteria);
        }
        let pageNumber = 0;
        let pageSize = this.numberOfRowsPerPage;
        let sortBy = 'id';
        let sortingOrder = 'DESC';
        let pagingAndFilterRequest: PagingAndFilterRequest = new PagingAndFilterRequest();
        pagingAndFilterRequest.filters = filters;
        pagingAndFilterRequest.pageNumber = pageNumber;
        pagingAndFilterRequest.pageSize = pageSize;
        pagingAndFilterRequest.sortBy = sortBy;
        pagingAndFilterRequest.sortingOrder = sortingOrder;
        this.loadRecordList(pagingAndFilterRequest);
    }
    loadRecordList(pagingAndFilterRequest: PagingAndFilterRequest) {
        this.showLoading(true);
        this.priceHistoryService.getProductsForUser( this.userDTO['id'], 
                this.userDTO.village.taluk['id'], this.userDTO.village.taluk.district['id'], this.userDTO.village.taluk.district.state['id'], pagingAndFilterRequest)
            .subscribe((pagingAndFilterResponse: PagingAndFilterResponse) => {
            this.totalRecords = pagingAndFilterResponse.totalRecords;
            if (pagingAndFilterResponse.results) {
                this.priceList = pagingAndFilterResponse.results;
            }
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Products');
        });
    }
    protected showDetailDialog(price: any) {
        console.log("showDetailDialog");
        const options: any = {
                viewContainerRef: this.vcRef,
                context: { price: price, qualities: this.qualities },
                fullscreen: false,
        };
        let ref: Promise<any> = this.dialogService.showModal(SellProductComponent, options);
        ref.then(result => {
            if (result) {
                this.lazyLoadRecordList();
            }
        }).catch(error => {console.log(error);});
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
    protected showLoading(value: boolean) {
        if (value) {
            this.loadingIndicator.show(this.loadingIndicatorOptions);
        } else {
            this.loadingIndicator.hide();
        }
    }
    hasPhoto(assets: any[]) {
        return assets && assets.length > 0;
    }
    getPhoto(assets: any[]) {
        var assetValue = assets[0]['assetValue'];
        assetValue = assetValue.replace(/^data:image\/[a-z]+;base64,/, "");
        return fromBase64(assetValue);
    }
}
