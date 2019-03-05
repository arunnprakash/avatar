import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { TruckDriverWareHouseMappingService } from "../../../../services/authorization/truckdriverwarehousemappingservice.generated";

@Component({
  selector: 'truckdriverwarehousemapping-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class TruckDriverWareHouseMappingDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(truckdriverwarehousemappingService: TruckDriverWareHouseMappingService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(truckdriverwarehousemappingService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit TruckDriverWareHouseMapping Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
