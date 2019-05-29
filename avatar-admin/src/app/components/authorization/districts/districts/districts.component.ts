import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { DistrictService } from "../../../../services/master/districtservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { DistrictDTO } from "../../../../services/master/districtdto.model";
import { DistrictDetailComponent } from "../district-detail/district-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'districts',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class DistrictsComponent extends BaseComponent implements OnInit {

    protected title = 'District';
    protected localCols: any[] = [{ field: 'districtName', header: 'DistrictName', dataType: 'INPUT' }];

    constructor( districtService: DistrictService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( districtService, authService, translate, domSanitizer, confirmationService, dialogService, DistrictDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.initDistrictField();
    }
    initDistrictField() {
        let menuItem: any = _.find(this.localCols, { 'field': 'districtName' });
        menuItem.field = this.languageCode;
    }
    protected initEmptyModel() {
        this.model = new DistrictDTO();
    }
}
