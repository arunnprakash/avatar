import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { SellerPriceHistoryService } from "../../../../services/product/sellerpricehistoryservice.generated";

@Component({
  selector: 'price-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class PriceDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(priceHistoryService: SellerPriceHistoryService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(priceHistoryService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit PriceHistory Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
