import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { SellerAgentCommissionService } from "../../../../services/master/selleragentcommissionservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { SellerAgentCommissionDTO } from "../../../../services/master/selleragentcommissiondto.model";
import { SellerAgentCommissionDetailComponent } from "../seller-agent-commission-detail/seller-agent-commission-detail.component";
import { TalukService } from '../../../../services/master/talukservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { MarketService } from '../../../../services/master/marketservice.generated';
import { MarketDTO } from "../../../../services/master/marketdto.model";

import * as _ from "lodash";

@Component( {
    selector: 'seller-agent-commissions',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class SellerAgentCommissionsComponent extends BaseComponent implements OnInit {

    protected title = 'Seller Agent Commission';
    protected localCols: any[] = [
           { field: 'commission', header: 'Commission', dataType: 'INPUT' }
    ];

    constructor( sellerAgentCommissionService: SellerAgentCommissionService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            private marketService: MarketService, private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( sellerAgentCommissionService, authService, translate, domSanitizer, confirmationService, dialogService, SellerAgentCommissionDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
    }
    protected initEmptyModel() {
        this.model = new SellerAgentCommissionDTO();
    }
}
