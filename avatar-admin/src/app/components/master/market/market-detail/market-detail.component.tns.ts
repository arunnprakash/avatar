import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { MarketService } from "../../../../services/master/marketservice.generated";

@Component({
  selector: 'market-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class MarketDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(marketService: MarketService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(marketService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Market Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
