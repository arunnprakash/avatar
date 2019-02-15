import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailDataViewTemplate } from '../../../base-detail/base.detail.dataView.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { ProductService } from "../../../../services/product/productservice.generated";

import * as _ from "lodash";

@Component({
  selector: 'product-detail',
  template: baseDetailDataViewTemplate,
  styles: [baseDetailCss]
})
export class ProductDetailComponent extends BaseDetailComponent implements OnInit {

    constructor(productService: ProductService, authService: AuthService, 
            params: ModalDialogParams, dialog: ModalDialogParams ) {
        super(productService, authService, params, dialog);
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit Product Detail Component.ts");
    }
    protected onSelectAutoComplete(field) {
        let menuItem: any = _.find(this.localCols, { 'field': 'assets' });
        menuItem.options = [this.model[field]];
    }
    protected isModelValid(): boolean {
        return true;
    }

}
