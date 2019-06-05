import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { WareHouseService } from "../../../../services/master/warehouseservice.generated";

@Component({
  selector: 'warehouse-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class WareHouseDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(warehouseService: WareHouseService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(warehouseService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit WareHouse Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
