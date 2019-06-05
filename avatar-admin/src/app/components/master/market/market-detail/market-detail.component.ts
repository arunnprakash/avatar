import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

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
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(marketService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Market Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
