import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { TalukService } from "../../../../services/authorization/talukservice.generated";

@Component({
  selector: 'taluk-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class TalukDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(talukService: TalukService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(talukService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Taluk Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
