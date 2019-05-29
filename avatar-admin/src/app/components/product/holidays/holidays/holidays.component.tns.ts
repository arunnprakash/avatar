import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component( {
    selector: 'holidays',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class HolidaysComponent extends BaseComponent implements OnInit {

    protected title = 'Holiday';
    protected localCols: any[] = [
               { field: 'startDate', header: 'StartDate', dataType: 'DATE'},
               { field: 'endDate', header: 'StartDate', dataType: 'DATE'},
               { field: 'description', header: 'Description', dataType: 'INPUT'},
               { field: 'state', header: 'State', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
               { field: 'district', header: 'District', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
               { field: 'taluk', header: 'Taluk', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"}
    ];
    constructor( holidayService: HolidayService, authService: AuthService, translate: TranslateService, 
            private stateService: StateService, private districtService: DistrictService, private talukService: TalukService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( holidayService, authService, translate, modalDialogService, dialogService, HolidayDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
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
            menuItem.originalOptions = states;
            states.forEach( (state: StateDTO ) => {
                menuItem.options.push(new TokenModel(state[this.languageCode], null));
            });
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
