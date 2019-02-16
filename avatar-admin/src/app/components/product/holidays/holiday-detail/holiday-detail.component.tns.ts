import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { HolidayService } from "../../../../services/product/holidayservice.generated";
import { DistrictDTO } from "../../../../services/authorization/districtdto.model";
import { DistrictService } from '../../../../services/authorization/districtservice.generated';
import { TalukDTO } from "../../../../services/authorization/talukdto.model";
import { TalukService } from '../../../../services/authorization/talukservice.generated';

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component({
  selector: 'holiday-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class HolidayDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(holidayService: HolidayService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams,
          private districtService: DistrictService, private talukService: TalukService) {
      super(holidayService, authService, params, dialog);
      this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Holiday Detail Component.tns");
  }
  protected onSelectAutoComplete(field) {
      if (field == 'state') {
          this.districtService.getAllExceptDeleted().subscribe((districts: DistrictDTO[]) => {
              let menuItem: any = _.find(this.localCols, { 'field': 'district' });
              menuItem.originalOptions = districts;
              menuItem.options = new ObservableArray<TokenModel>();
              districts.forEach( (district: DistrictDTO ) => {
                  menuItem.options.push(new TokenModel(district[this.languageCode], null));
              });
          },
          ( error ) => {
              this.showAlertDialog('Error', 'Error while getting State List');
          });
      } 
      if (field == 'district') {
          this.talukService.getAllExceptDeleted().subscribe((taluks: TalukDTO[]) => {
              let menuItem: any = _.find(this.localCols, { 'field': 'taluk' });
              menuItem.originalOptions = taluks;
              menuItem.options = new ObservableArray<TokenModel>();
              taluks.forEach( (taluk: TalukDTO ) => {
                  menuItem.options.push(new TokenModel(taluk[this.languageCode], null));
              });
          },
          ( error ) => {
              this.showAlertDialog('Error', 'Error while getting State List');
          });
      }
  }
  protected beforeSave() {
      if (this.model['state']) {
          this.model['state'] = this.model['state'].id;
      }
      if (this.model['district']) {
          this.model['district'] = this.model['district'].id;
      }
      if (this.model['taluk']) {
          this.model['taluk'] = this.model['taluk'].id;
      }
  }
  protected isModelValid(): boolean {
      return true;
  }
}
