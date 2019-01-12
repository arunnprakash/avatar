import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

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
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(roleService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Role Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
