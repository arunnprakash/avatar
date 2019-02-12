import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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
    providers: [ModalDialogService]
} )
export class StatesComponent extends BaseComponent implements OnInit {

    protected title = 'State';
    protected localCols: any[] = [{ field: 'stateName', header: 'StateName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( stateService: StateService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( stateService, authService, translate, modalDialogService, dialogService, StateDetailComponent, router, activatedRoute, vcRef);
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
