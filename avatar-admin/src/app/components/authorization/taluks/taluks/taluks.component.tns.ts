import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { TalukService } from "../../../../services/authorization/talukservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { TalukDTO } from "../../../../services/authorization/talukdto.model";
import { TalukDetailComponent } from "../taluk-detail/taluk-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'taluks',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class TaluksComponent extends BaseComponent implements OnInit {

    protected title = 'Taluk';
    protected localCols: any[] = [{ field: 'talukName', header: 'TalukName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( talukService: TalukService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( talukService, authService, translate, modalDialogService, dialogService, TalukDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initTalukField();
    }
    initTalukField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'talukName' });
        menuItem.field = this.languageCode;
    }
    protected initEmptyModel() {
        this.model = new TalukDTO();
    }
}
