import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { DistrictService } from "../../../../services/master/districtservice.generated";

@Component({
  selector: 'district-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class DistrictDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(districtService: DistrictService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(districtService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit District Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
