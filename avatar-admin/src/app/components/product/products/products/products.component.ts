import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";
import { baseDataViewTemplate } from '../../../base/base.dataView.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { ProductService } from '../../../../services/product/productservice.generated';
import { AssetTypeService } from "../../../../services/product/assettypeservice.generated";
import { AuthService } from "../../../../services/auth.service";

import { ProductDTO } from "../../../../services/product/productdto.model";
import { AssetTypeDTO } from "../../../../services/product/assettypedto.model";
import { ProductDetailComponent } from "../product-detail/product-detail.component";

import * as _ from "lodash";

@Component({
  selector: 'products',
  template: baseDataViewTemplate,
  styles: [baseCss],
  providers: [ConfirmationService, DialogService]
})
export class ProductsComponent extends BaseComponent implements OnInit {

    protected title = 'Product';
    protected localCols: any[] = [
         { field: 'productName', header: 'ProductName', dataType: 'INPUT' },
         { field: 'productCode', header: 'ProductCode', dataType: 'INPUT' },
         { field: 'assetTypes', header: 'AssetType', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"assetTypeName"},
         { field: 'assets', header: 'Assets', dataType: 'FILE', multiple: true, options: [] , optionLabel:"assetValue"}
    ];
    constructor( productService: ProductService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            private assetTypeService: AssetTypeService,
            confirmationService: ConfirmationService, dialogService: DialogService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef ) {
        super( productService, authService, translate, domSanitizer, confirmationService, dialogService, ProductDetailComponent, router, activatedRoute, vcRef );
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit product.component");
        this.initFieldsLabel("products");
        this.initAssetTypeList();
        this.initProductNameField();
    }
    initProductNameField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'productName' });
        menuItem.field = this.languageCode;
    }
    initAssetTypeList() {
        this.assetTypeService.getAllExceptDeleted().subscribe((assetTypes: AssetTypeDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'assetTypes' });
            menuItem.options = assetTypes;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Asset Type List');
        });
    }

    protected initEmptyModel() {
        this.model = new ProductDTO();
    }
    hasPhoto(assets: any[]){
        return assets && assets.length > 0;
    }
    getPhoto(assets: any[]){
        var assetValue = assets[0]['assetValue'];
        return this.domSanitizer.bypassSecurityTrustUrl(assetValue);
    }
    protected isModelValid(): boolean {
        return true;
    }
}
