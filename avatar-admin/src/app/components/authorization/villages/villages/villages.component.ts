import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { VillageService } from "../../../../services/authorization/villageservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { VillageDTO } from "../../../../services/authorization/villagedto.model";
import { VillageDetailComponent } from "../village-detail/village-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'villages',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class VillagesComponent extends BaseComponent implements OnInit {

    protected title = 'Village';
    protected localCols: any[] = [{ field: 'villageName', header: 'VillageName', dataType: 'INPUT' }];

    constructor( villageService: VillageService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( villageService, authService, translate, domSanitizer, confirmationService, dialogService, VillageDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initVillageField();
    }
    initVillageField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'villageName' });
        menuItem.field = this.languageCode;
    }
    protected initEmptyModel() {
        this.model = new VillageDTO();
    }
}
