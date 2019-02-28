import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { WareHouseService } from "../../../../services/authorization/warehouseservice.generated";

@Component({
  selector: 'warehouse-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class WareHouseDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(warehouseService: WareHouseService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(warehouseService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit WareHouse Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
