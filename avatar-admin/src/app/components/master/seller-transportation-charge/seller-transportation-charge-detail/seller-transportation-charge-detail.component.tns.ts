import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { SellerTransportationChargeService } from "../../../../services/master/sellertransportationchargeservice.generated";

@Component({
  selector: 'seller-transportation-charge-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class SellerTransportationChargeDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(sellerAgentCommissionService: SellerTransportationChargeService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(sellerAgentCommissionService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit SellerTransportationCharge Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
