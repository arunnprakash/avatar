import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { SellerMerchantCommissionService } from "../../../../services/master/sellermerchantcommissionservice.generated";

@Component({
  selector: 'seller-merchant-commission-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class SellerMerchantCommissionDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(sellerMerchantCommissionService: SellerMerchantCommissionService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(sellerMerchantCommissionService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit SellerMerchantCommission Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
