import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { HolidayService } from "../../../../services/product/holidayservice.generated";
import { StateService } from '../../../../services/master/stateservice.generated';
import { AuthService } from "../../../../services/auth.service";
import { StateDTO } from "../../../../services/master/statedto.model";
import { HolidayDTO } from "../../../../services/product/holidaydto.model";
import { HolidayDetailComponent } from "../holiday-detail/holiday-detail.component";
import { DistrictDTO } from "../../../../services/master/districtdto.model";
import { DistrictService } from '../../../../services/master/districtservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { TalukService } from '../../../../services/master/talukservice.generated';

import * as _ from "lodash";

@Component( {
    selector: 'holidays',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class HolidaysComponent extends BaseComponent implements OnInit {

    protected title = 'Holiday';
    protected localCols: any[] = [
              { field: 'startDate', header: 'StartDate', dataType: 'DATE'},
              { field: 'endDate', header: 'StartDate', dataType: 'DATE'},
              { field: 'description', header: 'Description', dataType: 'INPUT'},
              { field: 'state', header: 'State', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'district', header: 'District', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
              { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"}
    ];

    constructor( holidayService: HolidayService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            private stateService: StateService, private districtService: DistrictService, private talukService: TalukService,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( holidayService, authService, translate, domSanitizer, confirmationService, dialogService, HolidayDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit holiday.component");
        this.initFieldsLabel("holidays");
        this.initLocaleFields();
        this.initStateList();
    }
    initLocaleFields() {
        let menuItem: any = _.find(this.localCols, { 'field': 'state' });
        menuItem.optionLabel = this.languageCode;
        menuItem = _.find(this.localCols, { 'field': 'district' });
        menuItem.optionLabel = this.languageCode;
        menuItem = _.find(this.localCols, { 'field': 'taluk' });
        menuItem.optionLabel = this.languageCode;
    }
    initStateList() {
        this.stateService.getAllExceptDeleted().subscribe((states: StateDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'state' });
            menuItem.options = states;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting State List');
        });
    }
    protected recordListLoaded(): void {
        super.recordListLoaded();
        this.recordList.forEach( (holidayDTO: any ) => {
            this.stateService.get(holidayDTO.state).subscribe((state: StateDTO) => {
                holidayDTO.state = state;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting State');
            });
            this.districtService.get(holidayDTO.district).subscribe((district: DistrictDTO) => {
                holidayDTO.district = district;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting District');
            });
            this.talukService.get(holidayDTO.taluk).subscribe((taluk: TalukDTO) => {
                holidayDTO.taluk = taluk;
            },
            ( error ) => {
                this.showAlertDialog('Error', 'Error while getting Taluk');
            });
        });
    }
    protected initEmptyModel() {
        this.model = new HolidayDTO();
    }
}
