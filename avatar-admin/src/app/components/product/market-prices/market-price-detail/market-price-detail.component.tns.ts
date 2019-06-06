import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { MarketPriceService } from "../../../../services/product/marketpriceservice.generated";

@Component({
  selector: 'market-price-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class MarketPriceDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(marketPriceService: MarketPriceService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(marketPriceService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit PriceHistory Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
