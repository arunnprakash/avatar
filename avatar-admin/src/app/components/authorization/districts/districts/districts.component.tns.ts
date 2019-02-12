import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { DistrictService } from "../../../../services/authorization/districtservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { DistrictDTO } from "../../../../services/authorization/districtdto.model";
import { DistrictDetailComponent } from "../district-detail/district-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'districts',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class DistrictsComponent extends BaseComponent implements OnInit {

    protected title = 'District';
    protected localCols: any[] = [{ field: 'districtName', header: 'DistrictName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( districtService: DistrictService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( districtService, authService, translate, modalDialogService, dialogService, DistrictDetailComponent, router, activatedRoute, vcRef);
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
