import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { AssetTypeService } from "../../../../services/master/assettypeservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { AssetTypeDTO } from "../../../../services/master/assettypedto.model";
import { UsersAssetTypeDetailComponent } from "../assetType-detail/assetType-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'assetTypes',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class UsersAssetTypesComponent extends BaseComponent implements OnInit {

    protected title = 'AssetType';
    protected localCols: any[] = [{ field: 'assetTypeName', header: 'AssetTypeName', dataType: 'INPUT' }];

    constructor( assetTypeService: AssetTypeService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( assetTypeService, authService, translate, domSanitizer, confirmationService, dialogService, UsersAssetTypeDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }
    protected initEmptyModel() {
        this.model = new AssetTypeDTO();
    }
}
