import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { HolidayService } from "../../../../services/product/holidayservice.generated";
import { DistrictDTO } from "../../../../services/master/districtdto.model";
import { DistrictService } from '../../../../services/master/districtservice.generated';
import { TalukDTO } from "../../../../services/master/talukdto.model";
import { TalukService } from '../../../../services/master/talukservice.generated';

import * as _ from "lodash";

@Component({
  selector: 'holiday-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class HolidayDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(holidayService: HolidayService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef,
          private districtService: DistrictService, private talukService: TalukService) {
      super(holidayService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Holiday Detail Component.ts");
  }
  protected onSelectAutoComplete(field) {
      if (field == 'state') {
          this.districtService.getAllExceptDeleted().subscribe((districts: DistrictDTO[]) => {
              let menuItem: any = _.find(this.localCols, { 'field': 'district' });
              menuItem.options = districts;
          },
          ( error ) => {
              this.showAlertDialog('Error', 'Error while getting State List');
          });
      } 
      if (field == 'district') {
          this.talukService.getAllExceptDeleted().subscribe((taluks: TalukDTO[]) => {
              let menuItem: any = _.find(this.localCols, { 'field': 'taluk' });
              menuItem.options = taluks;
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
