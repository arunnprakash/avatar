import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { AssetTypeService } from "../../../../services/product/assettypeservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { AssetTypeDTO } from "../../../../services/product/assettypedto.model";
import { ProductsAssetTypeDetailComponent } from "../assetType-detail/assetType-detail.component";

@Component( {
    selector: 'assetTypes',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class ProductsAssetTypesComponent extends BaseComponent implements OnInit {

    protected title = 'AssetType';
    protected localCols: any[] = [{ field: 'assetTypeName', header: 'AssetTypeName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( assetTypeService: AssetTypeService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( assetTypeService, authService, translate, modalDialogService, dialogService, ProductsAssetTypeDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
    }

    protected initEmptyModel() {
        this.model = new AssetTypeDTO();
    }
}
