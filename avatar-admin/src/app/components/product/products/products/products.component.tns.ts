import { Component, OnInit, AfterViewInit , ViewContainerRef, ViewChildren, QueryList } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component({
  selector: 'products',
  template: baseDataViewTemplate,
  styles: [baseCss],
  providers: [ModalDialogService]
})
export class ProductsComponent extends BaseComponent implements OnInit, AfterViewInit  {

    tags: string[]=["Test"];
    protected title = 'Product';
    
    protected localCols: any[] = [
         { field: 'productName', header: 'ProductName', dataType: 'INPUT', autocapitalizationType: 'allCharacters', keyboardType: 'email' },
         { field: 'productCode', header: 'ProductCode', dataType: 'INPUT', autocapitalizationType: 'allCharacters', keyboardType: 'email'  },
         { field: 'assetTypes', header: 'AssetType', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"assetTypeName", "onSelect": this.assetTypeSelected},
         { field: 'assets', header: 'Assets', dataType: 'FILE', multiple: true, options: [] , optionLabel:"assetValue"}
    ];
    constructor( productService: ProductService, authService: AuthService, translate: TranslateService, 
            private assetTypeService: AssetTypeService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( productService, authService, translate, modalDialogService, dialogService, ProductDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngAfterViewInit() {
        
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit product.component.tns");
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
            let menuItem: any = _.find(this.localCols, { 'field': 'assets' });
            menuItem.options = assetTypes;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Asset Type List');
        });
    }
    assetTypeSelected(value) {
        console.log("assetTypeSelected");
    }
    protected initEmptyModel() {
        this.model = new ProductDTO();
    }

    protected isModelValid(): boolean {
        return true;
    }
}
