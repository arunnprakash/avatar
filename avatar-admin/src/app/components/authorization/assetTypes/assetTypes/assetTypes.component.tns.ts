import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { AssetTypeService } from "../../../../services/authorization/assettypeservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { AssetTypeDTO } from "../../../../services/authorization/assetTypedto.model";
import { AssetTypeDetailComponent } from "../assetType-detail/assetType-detail.component";

@Component( {
    selector: 'assetTypes',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class AssetTypesComponent extends BaseComponent implements OnInit {

    protected title = 'AssetType';
    protected localCols: any[] = [{ field: 'assetTypeName', header: 'AssetTypeName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( assetTypeService: AssetTypeService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( assetTypeService, authService, translate, modalDialogService, dialogService, AssetTypeDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }

    protected initEmptyModel() {
        this.model = new AssetTypeDTO();
    }
}
