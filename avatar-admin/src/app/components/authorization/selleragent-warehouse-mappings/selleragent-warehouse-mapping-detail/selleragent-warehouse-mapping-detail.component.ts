import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

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
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(selleragentwarehousemappingService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit SellerAgentWareHouseMapping Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
