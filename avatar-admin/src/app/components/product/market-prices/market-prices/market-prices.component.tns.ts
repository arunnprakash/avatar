import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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
import { MarketService } from '../../../../services/master/marketservice.generated';
import { MarketDTO } from "../../../../services/master/marketdto.model";

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'market-prices',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class MarketPricesComponent extends BaseComponent implements OnInit {

    protected title = 'Price';
    protected localCols: any[] = [
                                  { field: 'market', header: 'Market', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en" },
                                  { field: 'product', header: 'Product', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
                                  { field: 'quality', header: 'Quality', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"qualityType"},
                                  { field: 'price', header: 'Price', dataType: 'INPUT' }
                        ];
    constructor( marketPriceService: MarketPriceService, authService: AuthService, translate: TranslateService, 
            private marketService: MarketService, private productService: ProductService, private qualityService: QualityService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( marketPriceService, authService, translate, modalDialogService, dialogService, MarketPriceDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        this.initFieldsLabel("prices");
        this.initMarketList();
        this.initProductList();
        this.initQualityList();
        this.initProductNameField();
    }
    initProductNameField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'product' });
        menuItem.optionLabel = this.languageCode;
    }
    initMarketList() {
        this.marketService.getAllExceptDeleted().subscribe((markets: MarketDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'market' });
            menuItem.optionLabel = this.languageCode;
            menuItem.originalOptions = markets;
            markets.forEach( (market: MarketDTO ) => {
                menuItem.options.push(new TokenModel(market[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Market List');
        });
    }
    initProductList() {
        this.productService.getAllExceptDeleted().subscribe((products: ProductDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'product' });
            menuItem.originalOptions = products;
            products.forEach( (product: ProductDTO ) => {
                menuItem.options.push(new TokenModel(product[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Product List');
        });
    }
    initQualityList() {
        this.qualityService.getAllExceptDeleted().subscribe((qualities: QualityDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'quality' });
            menuItem.originalOptions = qualities;
            qualities.forEach( (quality: QualityDTO ) => {
                menuItem.options.push(new TokenModel(quality.qualityType, null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Quality List');
        });
    }
    protected initEmptyModel() {
        this.model = new MarketPriceDTO();
    }
}
