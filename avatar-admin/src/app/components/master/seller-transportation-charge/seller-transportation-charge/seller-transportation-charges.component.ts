import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { SellerTransportationChargeService } from "../../../../services/master/sellertransportationchargeservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { SellerTransportationChargeDTO } from "../../../../services/master/sellertransportationchargedto.model";
import { SellerTransportationChargeDetailComponent } from "../seller-transportation-charge-detail/seller-transportation-charge-detail.component";
import { TalukService } from '../../../../services/master/talukservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { MarketService } from '../../../../services/master/marketservice.generated';
import { MarketDTO } from "../../../../services/master/marketdto.model";

import * as _ from "lodash";

@Component( {
    selector: 'seller-transportation-charges',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class SellerTransportationChargesComponent extends BaseComponent implements OnInit {

    protected title = 'Seller Transportation Charge';
    protected localCols: any[] = [
           { field: 'transportationCharge', header: 'Transportation Charge', dataType: 'INPUT' },
           { field: 'exemption', header: 'Exemption', dataType: 'INPUT' }
    ];

    constructor( sellerAgentCommissionService: SellerTransportationChargeService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private marketService: MarketService, private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( sellerAgentCommissionService, authService, translate, domSanitizer, confirmationService, dialogService, SellerTransportationChargeDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
    }
    protected initEmptyModel() {
        this.model = new SellerTransportationChargeDTO();
    }
}
