import { Component, OnInit } from '@angular/core';

import { ModalDialogParams } from "nativescript-angular/modal-dialog";

import { baseDetailDataViewTemplate } from '../../../base-detail/base.detail.dataView.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';

import { UserService } from "../../../../services/authorization/userservice.generated";

@Component({
  selector: 'user-detail',
  template: baseDetailDataViewTemplate,
  styles: [baseDetailCss]
})
export class UserDetailComponent extends BaseDetailComponent implements OnInit {

    constructor(userService: UserService, params: ModalDialogParams, dialog: ModalDialogParams ) {
        super(userService, params, dialog);
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit User Detail Component.ts");
    }
    protected isModelValid(): boolean {
        return true;
    }

}
