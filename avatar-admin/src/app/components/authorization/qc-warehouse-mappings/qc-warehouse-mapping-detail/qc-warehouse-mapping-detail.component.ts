import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { QcWareHouseMappingService } from "../../../../services/authorization/qcwarehousemappingservice.generated";

@Component({
  selector: 'qcwarehousemapping-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class QcWareHouseMappingDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(qcwarehousemappingService: QcWareHouseMappingService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(qcwarehousemappingService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit QcWareHouseMapping Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
