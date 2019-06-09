import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { SellerAgentCommissionService } from "../../../../services/master/selleragentcommissionservice.generated";

@Component({
  selector: 'seller-agent-commission-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class SellerAgentCommissionDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(sellerAgentCommissionService: SellerAgentCommissionService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(sellerAgentCommissionService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit SellerAgentCommission Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
