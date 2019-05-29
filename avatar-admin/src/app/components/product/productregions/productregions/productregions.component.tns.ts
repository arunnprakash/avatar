import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { ProductRegionService } from "../../../../services/product/productregionservice.generated";
import { ProductService } from '../../../../services/product/productservice.generated';
import { StateService } from '../../../../services/master/stateservice.generated';
import { AuthService } from "../../../../services/auth.service";
import { StateDTO } from "../../../../services/master/statedto.model";
import { ProductDTO } from "../../../../services/product/productdto.model";
import { ProductRegionDTO } from "../../../../services/product/productregiondto.model";
import { DistrictDTO } from "../../../../services/master/districtdto.model";
import { DistrictService } from '../../../../services/master/districtservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { TalukService } from '../../../../services/master/talukservice.generated';
import { ProductRegionDetailComponent } from "../productregion-detail/productregion-detail.component";

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'productregions',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class ProductRegionsComponent extends BaseComponent implements OnInit {

    protected title = 'ProductRegion';
    protected localCols: any[] = [
          { field: 'product', header: 'Product', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
          { field: 'state', header: 'State', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
          { field: 'district', header: 'District', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
          { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"}
    ];
    constructor( productregionService: ProductRegionService, authService: AuthService, translate: TranslateService, 
            private productService: ProductService, private stateService: StateService, private districtService: DistrictService, private talukService: TalukService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( productregionService, authService, translate, modalDialogService, dialogService, ProductRegionDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        this.initFieldsLabel("productregions");
        this.initProductList();
        this.initStateList();
        this.initLocaleFields();
    }
    initLocaleFields() {
        let menuItem: any = _.find(this.localCols, { 'field': 'product' });
        menuItem.optionLabel = this.languageCode;
        menuItem = _.find(this.localCols, { 'field': 'state' });
        menuItem.optionLabel = this.languageCode;
        menuItem = _.find(this.localCols, { 'field': 'district' });
        menuItem.optionLabel = this.languageCode;
        menuItem = _.find(this.localCols, { 'field': 'taluk' });
        menuItem.optionLabel = this.languageCode;
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
    initStateList() {
        this.stateService.getAllExceptDeleted().subscribe((states: StateDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'state' });
            menuItem.originalOptions = states;
            states.forEach( (state: StateDTO ) => {
                menuItem.options.push(new TokenModel(state[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting State List');
        });
    }
    protected recordListLoaded(): void {
        super.recordListLoaded();
        this.recordList.forEach( (productRegionDTO: any ) => {
            this.stateService.get(productRegionDTO.state).subscribe((state: StateDTO) => {
                productRegionDTO.state = state;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting State');
            });
            this.districtService.get(productRegionDTO.district).subscribe((district: DistrictDTO) => {
                productRegionDTO.district = district;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting District');
            });
            this.talukService.get(productRegionDTO.taluk).subscribe((taluk: TalukDTO) => {
                productRegionDTO.taluk = taluk;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting Taluk');
            });
        });
    }
    protected initEmptyModel() {
        this.model = new ProductRegionDTO();
    }
}
