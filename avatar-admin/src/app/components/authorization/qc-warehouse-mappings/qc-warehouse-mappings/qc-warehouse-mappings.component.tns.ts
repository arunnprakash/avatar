import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'qcwarehousemappings',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class QcWareHouseMappingsComponent extends BaseComponent implements OnInit {

    protected title = 'QcWareHouseMapping';
    protected localCols: any[] = [
           { field: 'qc', header: 'Qc', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"firstName"},
           { field: 'wareHouse', header: 'WareHouse', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"name"}
    ];

    constructor( qcwarehousemappingService: QcWareHouseMappingService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            private userService: UserService, private warehouseService: WareHouseService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( qcwarehousemappingService, authService, translate, modalDialogService, dialogService, QcWareHouseMappingDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit qc-warehouse-mapping.component.tns");
        this.initQcList();
        this.initWareHouseList();
    }
    initWareHouseList() {
        this.warehouseService.getAllExceptDeleted().subscribe((wareHouses: WareHouseDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'wareHouse' });
            menuItem.originalOptions = wareHouses;
            wareHouses.forEach( (wareHouse: WareHouseDTO ) => {
                menuItem.options.push(new TokenModel(wareHouse['name'], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting WareHouse List');
        });
    }
    initQcList() {
        this.userService.getAllExceptDeleted().subscribe((users: UserDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'qc' });
            menuItem.originalOptions = users;
            users.forEach( (user: UserDTO ) => {
                menuItem.options.push(new TokenModel(user['firstName'], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Qc List');
        });
    }
    protected initEmptyModel() {
        this.model = new QcWareHouseMappingDTO();
    }
}
