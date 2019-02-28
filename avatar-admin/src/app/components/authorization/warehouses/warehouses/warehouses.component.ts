import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { WareHouseService } from "../../../../services/authorization/warehouseservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { WareHouseDTO } from "../../../../services/authorization/warehousedto.model";
import { WareHouseDetailComponent } from "../warehouse-detail/warehouse-detail.component";
import { TalukService } from '../../../../services/authorization/talukservice.generated';
import { TalukDTO } from "../../../../services/authorization/talukdto.model";

import * as _ from "lodash";

@Component( {
    selector: 'warehouses',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class WareHousesComponent extends BaseComponent implements OnInit {

    protected title = 'WareHouse';
    protected localCols: any[] = [
           { field: 'name', header: 'WareHouseName', dataType: 'INPUT' },
           { field: 'address', header: 'Address', dataType: 'TEXTAREA' },
           { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
           { field: 'latitude', header: 'Latitude', dataType: 'INPUT' },
           { field: 'longitude', header: 'Longitude', dataType: 'INPUT' }
    ];

    constructor( warehouseService: WareHouseService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( warehouseService, authService, translate, domSanitizer, confirmationService, dialogService, WareHouseDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initTalukList();
    }
    initTalukList() {
        this.talukService.getAllExceptDeleted().subscribe((taluks: TalukDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'taluk' });
            menuItem.optionLabel = this.languageCode;
            menuItem.options = taluks;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Taluk List');
        });
    }
    protected initEmptyModel() {
        this.model = new WareHouseDTO();
    }
}
