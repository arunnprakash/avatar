import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { LanguageService } from "../../../../services/master/languageservice.generated";

@Component({
  selector: 'language-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class LanguageDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(languageService: LanguageService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(languageService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Language Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
