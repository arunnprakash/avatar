import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { StateService } from "../../../../services/authorization/stateservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { StateDTO } from "../../../../services/authorization/statedto.model";
import { StateDetailComponent } from "../state-detail/state-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'states',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class StatesComponent extends BaseComponent implements OnInit {

    protected title = 'State';
    protected localCols: any[] = [{ field: 'stateName', header: 'StateName', dataType: 'INPUT' }];

    constructor( stateService: StateService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( stateService, authService, translate, domSanitizer, confirmationService, dialogService, StateDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initStateField();
    }
    initStateField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'stateName' });
        menuItem.field = this.languageCode;
    }
    protected initEmptyModel() {
        this.model = new StateDTO();
    }
}
