import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { DynamicDialogRef, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "../../../services/auth.service";
import { UserDTO } from "../../../services/authorization/userdto.model";
import { QualityDTO } from "../../../services/product/qualitydto.model";
import { QualityService } from '../../../services/product/qualityservice.generated';
import { PagingAndFilterRequest } from "../../../services/product/pagingandfilterrequest.model";
import { FilterCriteria } from "../../../services/product/filtercriteria.model";
import { PagingAndFilterResponse } from "../../../services/product/pagingandfilterresponse.model";
import { SellerPriceHistoryService } from '../../../services/product/sellerpricehistoryservice.generated';

import { SellProductComponent } from "./sell-product/sell-product.component";

import * as _ from "lodash";


@Component({
  selector: 'seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css'],
  providers: [DialogService]
})
export class SellerComponent implements OnInit {
    protected numberOfRowsPerPage = 8;
    protected priceList: any[];
    protected totalRecords: number;
    protected languageCode: string;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    protected loading: boolean;
    protected userDTO: UserDTO;
    protected qualities: QualityDTO[];
    constructor(private authService: AuthService, private priceHistoryService: SellerPriceHistoryService,
            private qualityService: QualityService,
            private dialogService: DialogService, private translate: TranslateService, private domSanitizer: DomSanitizer,
            private router: Router, private activatedRoute: ActivatedRoute) { 
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.info( "OnInit Seller Component tns" );
        this.priceList = [];
        this.displayAlertDialog = false;
        this.loading = false;
        this.initQualityList();
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
    protected showLoading(value: boolean) {
        this.loading = value;
    }
    protected showDetailDialog(price: any) {
        let ref: DynamicDialogRef = this.dialogService.open(SellProductComponent, {
            data: {
                price: price, qualities: this.qualities
            },
            header: price.product[this.languageCode],
            width: '50%',
            height: '70%'
        });
        ref.onClose.subscribe((saved: boolean) => {
            if (saved) {
                this.showAlertDialog('Success', 'Successfully Saved. Your Agent AgentName contact You.');
                this.lazyLoadRecordList();
            }
        });
    }
    protected showAlertDialog(title: string, message: string) {
        this.displayAlertDialog = true;
        this.alertDialogTitle = title;
        this.alertDialogMessage = message;
    }
    hasPhoto(assets: any[]) {
        return assets && assets.length > 0;
    }
    getPhoto(assets: any[]) {
        var assetValue = assets[0]['assetValue'];
        return this.domSanitizer.bypassSecurityTrustUrl(assetValue);
    }
}
