import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { AssetTypeService } from "../../../../services/authorization/assettypeservice.generated";

@Component({
  selector: 'assetType-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class UsersAssetTypeDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(assetTypeService: AssetTypeService, authService: AuthService, 
          params: ModalDialogParams, dialog: ModalDialogParams) {
      super(assetTypeService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit AssetType Detail Component.tns");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
