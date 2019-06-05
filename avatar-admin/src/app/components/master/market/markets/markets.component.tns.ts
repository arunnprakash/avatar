import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'markets',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class MarketsComponent extends BaseComponent implements OnInit {

    protected title = 'Market';
    protected localCols: any[] = [
           { field: 'name', header: 'MarketName', dataType: 'INPUT', autocapitalizationType:'none', keyboardType: 'email' },
           { field: 'address', header: 'Address', dataType: 'TEXTAREA', autocapitalizationType:'none', keyboardType: 'email' },
           { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
           { field: 'latitude', header: 'Latitude', dataType: 'INPUT' },
           { field: 'longitude', header: 'Longitude', dataType: 'INPUT' }
    ];

    constructor( marketService: MarketService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            private talukService: TalukService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( marketService, authService, translate, modalDialogService, dialogService, MarketDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit market.component.tns");
        this.initTalukList();
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
        this.model = new MarketDTO();
    }
}
