import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { SellerAgentWareHouseMappingService } from "../../../../services/authorization/selleragentwarehousemappingservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { SellerAgentWareHouseMappingDTO } from "../../../../services/authorization/selleragentwarehousemappingdto.model";
import { SellerAgentWareHouseMappingDetailComponent } from "../selleragent-warehouse-mapping-detail/selleragent-warehouse-mapping-detail.component";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { WareHouseDTO } from "../../../../services/master/warehousedto.model";
import { UserService } from '../../../../services/authorization/userservice.generated';
import { WareHouseService } from "../../../../services/master/warehouseservice.generated";

import * as _ from "lodash";

@Component( {
    selector: 'selleragentwarehousemappings',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class SellerAgentWareHouseMappingsComponent extends BaseComponent implements OnInit {

    protected title = 'SellerAgentWareHouseMapping';
    protected localCols: any[] = [
           { field: 'sellerAgent', header: 'SellerAgent', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"firstName"},
           { field: 'wareHouse', header: 'WareHouse', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"name"}
    ];

    constructor( selleragentwarehousemappingService: SellerAgentWareHouseMappingService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private userService: UserService, private warehouseService: WareHouseService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( selleragentwarehousemappingService, authService, translate, domSanitizer, confirmationService, dialogService, SellerAgentWareHouseMappingDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initSellerAgentList();
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
    initSellerAgentList() {
        this.userService.getAllExceptDeleted().subscribe((users: UserDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'sellerAgent' });
            menuItem.options = users;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting SellerAgent List');
        });
    }
    protected initEmptyModel() {
        this.model = new SellerAgentWareHouseMappingDTO();
    }
}
