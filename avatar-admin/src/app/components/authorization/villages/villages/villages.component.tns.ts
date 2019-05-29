import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { VillageService } from "../../../../services/master/villageservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { VillageDTO } from "../../../../services/master/villagedto.model";
import { VillageDetailComponent } from "../village-detail/village-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'villages',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class VillagesComponent extends BaseComponent implements OnInit {

    protected title = 'Village';
    protected localCols: any[] = [{ field: 'villageName', header: 'VillageName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( villageService: VillageService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( villageService, authService, translate, modalDialogService, dialogService, VillageDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit village.component.tns");
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
