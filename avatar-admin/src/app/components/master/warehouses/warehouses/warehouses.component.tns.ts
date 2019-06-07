import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { WareHouseService } from "../../../../services/master/warehouseservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { WareHouseDTO } from "../../../../services/master/warehousedto.model";
import { WareHouseDetailComponent } from "../warehouse-detail/warehouse-detail.component";
import { TalukService } from '../../../../services/master/talukservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { MarketService } from '../../../../services/master/marketservice.generated';
import { MarketDTO } from "../../../../services/master/marketdto.model";

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'warehouses',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class WareHousesComponent extends BaseComponent implements OnInit {

    protected title = 'WareHouse';
    protected localCols: any[] = [
           { field: 'wareHouseName', header: 'WareHouseName', dataType: 'INPUT', autocapitalizationType:'none', keyboardType: 'email' },
           { field: 'market', header: 'Market', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en" },
           { field: 'address', header: 'Address', dataType: 'TEXTAREA', autocapitalizationType:'none', keyboardType: 'email' },
           { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
           { field: 'latitude', header: 'Latitude', dataType: 'INPUT' },
           { field: 'longitude', header: 'Longitude', dataType: 'INPUT' }
    ];

    constructor( warehouseService: WareHouseService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            private marketService: MarketService, private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( warehouseService, authService, translate, modalDialogService, dialogService, WareHouseDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit warehouse.component.tns");
        this.initWareHouseNameField();
        this.initMarketList();
        this.initTalukList();
    }
    initWareHouseNameField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'wareHouseName' });
        menuItem.field = this.languageCode;
    }
    initMarketList() {
        this.marketService.getAllExceptDeleted().subscribe((markets: MarketDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'market' });
            menuItem.optionLabel = this.languageCode;
            menuItem.originalOptions = markets;
            markets.forEach( (market: MarketDTO ) => {
                menuItem.options.push(new TokenModel(market[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Market List');
        });
    }
    initTalukList() {
        this.talukService.getAllExceptDeleted().subscribe((taluks: TalukDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'taluk' });
            menuItem.optionLabel = this.languageCode;
            menuItem.originalOptions = taluks;
            taluks.forEach( (taluk: TalukDTO ) => {
                menuItem.options.push(new TokenModel(taluk[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Taluk List');
        });
    }
    protected initEmptyModel() {
        this.model = new WareHouseDTO();
    }
}
