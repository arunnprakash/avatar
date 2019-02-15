import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { PriceHistoryService } from "../../../../services/product/pricehistoryservice.generated";

@Component({
  selector: 'price-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class PriceDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(priceHistoryService: PriceHistoryService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(priceHistoryService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit PriceHistory Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
