import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { SellerAgentWareHouseMappingService } from "../../../../services/authorization/selleragentwarehousemappingservice.generated";

@Component({
  selector: 'selleragentwarehousemapping-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class SellerAgentWareHouseMappingDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(selleragentwarehousemappingService: SellerAgentWareHouseMappingService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(selleragentwarehousemappingService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit SellerAgentWareHouseMapping Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
