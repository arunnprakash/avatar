import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'seller-agent-commissions',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class SellerAgentCommissionsComponent extends BaseComponent implements OnInit {

    protected title = 'Seller Agent Commission';
    protected localCols: any[] = [
           { field: 'commission', header: 'Commission', dataType: 'INPUT' }
    ];

    constructor( sellerAgentCommissionService: SellerAgentCommissionService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            private marketService: MarketService, private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( sellerAgentCommissionService, authService, translate, modalDialogService, dialogService, SellerAgentCommissionDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit SellerAgentCommissions.component.tns");
    }
    protected initEmptyModel() {
        this.model = new SellerAgentCommissionDTO();
    }
}
