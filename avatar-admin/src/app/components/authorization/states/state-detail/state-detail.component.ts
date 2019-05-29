import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { StateService } from "../../../../services/master/stateservice.generated";

@Component({
  selector: 'state-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class StateDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(stateService: StateService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(stateService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit State Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
