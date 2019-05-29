import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { TalukService } from "../../../../services/master/talukservice.generated";

@Component({
  selector: 'taluk-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class TalukDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(talukService: TalukService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(talukService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Taluk Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
