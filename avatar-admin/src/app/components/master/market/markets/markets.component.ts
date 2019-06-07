import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { MarketService } from "../../../../services/master/marketservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { MarketDTO } from "../../../../services/master/marketdto.model";
import { MarketDetailComponent } from "../market-detail/market-detail.component";
import { TalukService } from '../../../../services/master/talukservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";

import * as _ from "lodash";

@Component( {
    selector: 'markets',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class MarketsComponent extends BaseComponent implements OnInit {

    protected title = 'Market';
    protected localCols: any[] = [
           { field: 'marketName', header: 'MarketName', dataType: 'INPUT' },
           { field: 'address', header: 'Address', dataType: 'TEXTAREA' },
           { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
           { field: 'latitude', header: 'Latitude', dataType: 'INPUT' },
           { field: 'longitude', header: 'Longitude', dataType: 'INPUT' }
    ];

    constructor( marketService: MarketService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( marketService, authService, translate, domSanitizer, confirmationService, dialogService, MarketDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initMarketNameField();
        this.initTalukList();
    }
    initMarketNameField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'marketName' });
        menuItem.field = this.languageCode;
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
        this.model = new MarketDTO();
    }
}
