import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { MarketPriceService } from "../../../../services/product/marketpriceservice.generated";
import { ProductService } from '../../../../services/product/productservice.generated';
import { QualityService } from '../../../../services/product/qualityservice.generated';
import { AuthService } from "../../../../services/auth.service";
import { ProductDTO } from "../../../../services/product/productdto.model";
import { QualityDTO } from "../../../../services/product/qualitydto.model";
import { MarketPriceDTO } from "../../../../services/product/marketpricedto.model";
import { MarketPriceDetailComponent } from "../market-price-detail/market-price-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'market-prices',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class MarketPricesComponent extends BaseComponent implements OnInit {

    protected title = 'Price';
    protected localCols: any[] = [
              { field: 'product', header: 'Product', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'quality', header: 'Quality', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"qualityType"},
              { field: 'price', header: 'Price', dataType: 'INPUT' }
    ];

    constructor( marketPriceService: MarketPriceService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            private productService: ProductService, private qualityService: QualityService,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( marketPriceService, authService, translate, domSanitizer, confirmationService, dialogService, MarketPriceDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit market-price.component");
        this.initFieldsLabel("prices");
        this.initProductList();
        this.initQualityList();
        this.initProductNameField();
    }
    initProductNameField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'product' });
        menuItem.optionLabel = this.languageCode;
    }
    initProductList() {
        this.productService.getAllExceptDeleted().subscribe((products: ProductDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'product' });
            menuItem.options = products;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Product List');
        });
    }
    initQualityList() {
        this.qualityService.getAllExceptDeleted().subscribe((qualities: QualityDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'quality' });
            menuItem.options = qualities;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Quality List');
        });
    }
    protected initEmptyModel() {
        this.model = new MarketPriceDTO();
    }
}
