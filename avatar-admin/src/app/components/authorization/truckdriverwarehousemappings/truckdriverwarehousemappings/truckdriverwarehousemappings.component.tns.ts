import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { TruckDriverWareHouseMappingService } from "../../../../services/authorization/truckdriverwarehousemappingservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { TruckDriverWareHouseMappingDTO } from "../../../../services/authorization/truckdriverwarehousemappingdto.model";
import { TruckDriverWareHouseMappingDetailComponent } from "../truckdriverwarehousemapping-detail/truckdriverwarehousemapping-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'truckdriverwarehousemappings',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class TruckDriverWareHouseMappingsComponent extends BaseComponent implements OnInit {

    protected title = 'TruckDriverWareHouseMapping';
    protected localCols: any[] = [{ field: 'truckdriverwarehousemappingName', header: 'TruckDriverWareHouseMappingName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( truckdriverwarehousemappingService: TruckDriverWareHouseMappingService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( truckdriverwarehousemappingService, authService, translate, modalDialogService, dialogService, TruckDriverWareHouseMappingDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit truckdriverwarehousemapping.component.tns");
        this.initTruckDriverWareHouseMappingField();
    }
    initTruckDriverWareHouseMappingField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'truckdriverwarehousemappingName' });
        menuItem.field = this.languageCode;
    }
    protected initEmptyModel() {
        this.model = new TruckDriverWareHouseMappingDTO();
    }
}
