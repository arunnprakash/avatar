import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { TruckDriverWareHouseMappingService } from "../../../../services/authorization/truckdriverwarehousemappingservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { TruckDriverWareHouseMappingDTO } from "../../../../services/authorization/truckdriverwarehousemappingdto.model";
import { TruckDriverWareHouseMappingDetailComponent } from "../truckdriver-warehouse-mapping-detail/truckdriver-warehouse-mapping-detail.component";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { WareHouseDTO } from "../../../../services/master/warehousedto.model";
import { UserService } from '../../../../services/authorization/userservice.generated';
import { WareHouseService } from "../../../../services/master/warehouseservice.generated";

import * as _ from "lodash";

@Component( {
    selector: 'truckdriverwarehousemappings',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class TruckDriverWareHouseMappingsComponent extends BaseComponent implements OnInit {

    protected title = 'TruckDriverWareHouseMapping';
    protected localCols: any[] = [
           { field: 'truckDriver', header: 'TruckDriver', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"firstName"},
           { field: 'wareHouse', header: 'WareHouse', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"name"}
    ];

    constructor( truckdriverwarehousemappingService: TruckDriverWareHouseMappingService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private userService: UserService, private warehouseService: WareHouseService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( truckdriverwarehousemappingService, authService, translate, domSanitizer, confirmationService, dialogService, TruckDriverWareHouseMappingDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initTruckDriverList();
        this.initWareHouseList();
    }
    initWareHouseList() {
        this.warehouseService.getAllExceptDeleted().subscribe((wareHouses: WareHouseDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'wareHouse' });
            menuItem.options = wareHouses;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting WareHouse List');
        });
    }
    initTruckDriverList() {
        this.userService.getAllExceptDeleted().subscribe((users: UserDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'truckDriver' });
            menuItem.options = users;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting TruckDriver List');
        });
    }
    protected initEmptyModel() {
        this.model = new TruckDriverWareHouseMappingDTO();
    }
}
