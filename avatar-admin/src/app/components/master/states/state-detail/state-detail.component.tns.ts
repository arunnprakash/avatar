import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

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
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(stateService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit State Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
