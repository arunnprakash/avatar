import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { QcWareHouseMappingService } from "../../../../services/authorization/qcwarehousemappingservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { QcWareHouseMappingDTO } from "../../../../services/authorization/qcwarehousemappingdto.model";
import { QcWareHouseMappingDetailComponent } from "../qc-warehouse-mapping-detail/qc-warehouse-mapping-detail.component";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { WareHouseDTO } from "../../../../services/master/warehousedto.model";
import { UserService } from '../../../../services/authorization/userservice.generated';
import { WareHouseService } from "../../../../services/master/warehouseservice.generated";

import * as _ from "lodash";

@Component( {
    selector: 'qcwarehousemappings',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class QcWareHouseMappingsComponent extends BaseComponent implements OnInit {

    protected title = 'QcWareHouseMapping';
    protected localCols: any[] = [
           { field: 'qc', header: 'Qc', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"firstName"},
           { field: 'wareHouse', header: 'WareHouse', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"name"}
    ];

    constructor( qcwarehousemappingService: QcWareHouseMappingService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private userService: UserService, private warehouseService: WareHouseService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( qcwarehousemappingService, authService, translate, domSanitizer, confirmationService, dialogService, QcWareHouseMappingDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initQcList();
        this.initWareHouseList();
    }
    initWareHouseList() {
        this.warehouseService.getAllExceptDeleted().subscribe((wareHouses: WareHouseDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'wareHouse' });
        	menuItem.optionLabel = this.languageCode;
            menuItem.options = wareHouses;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting WareHouse List');
        });
    }
    initQcList() {
        this.userService.getAllExceptDeleted().subscribe((users: UserDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'qc' });
            menuItem.options = users;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Qc List');
        });
    }
    protected initEmptyModel() {
        this.model = new QcWareHouseMappingDTO();
    }
}
