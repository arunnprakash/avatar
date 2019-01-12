import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { RoleService } from "../../../../services/authorization/roleservice.generated";

@Component({
  selector: 'role-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class RoleDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(roleService: RoleService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(roleService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Role Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
