import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
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


import * as _ from "lodash";

@Component( {
    selector: 'productregions',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class ProductRegionsComponent extends BaseComponent implements OnInit {

    protected title = 'ProductRegion';
    protected localCols: any[] = [
              { field: 'product', header: 'Product', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'state', header: 'State', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'district', header: 'District', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"}
    ];

    constructor( productregionService: ProductRegionService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            private productService: ProductService, private stateService: StateService, private districtService: DistrictService, private talukService: TalukService,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( productregionService, authService, translate, domSanitizer, confirmationService, dialogService, ProductRegionDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit productregion.component");
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
            menuItem.options = products;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Product List');
        });
    }
    initStateList() {
        this.stateService.getAllExceptDeleted().subscribe((states: StateDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'state' });
            menuItem.options = states;
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
