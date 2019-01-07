import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { ConfirmationService, DialogService } from 'primeng/api';
import { baseDataViewTemplate } from '../../../base/base.dataView.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { UserService } from '../../../../services/authorization/userservice.generated';
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { UserDetailComponent } from "../user-detail/user-detail.component";

@Component({
  selector: 'users',
  template: baseDataViewTemplate,
  styles: [baseCss],
  providers: [ConfirmationService, DialogService]
})
export class UsersComponent extends BaseComponent implements OnInit {

    protected title = 'User';
    protected localCols: any[] = [
         { field: 'userName', header: 'UserName', dataType: 'INPUT' },
         { field: 'mobileNumber', header: 'MobileNumber', dataType: 'INPUT' },
         { field: 'firstName', header: 'FirstName', dataType: 'INPUT' },
         { field: 'lastName', header: 'LastName', dataType: 'INPUT' }
    ];
    constructor( userService: UserService, confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef ) {
        super( userService, confirmationService, dialogService,UserDetailComponent, router, activatedRoute, vcRef );
    }

    ngOnInit() {
        super.ngOnInit();
    }

    protected initEmptyModel() {
        this.model = new UserDTO();
    }

    protected isModelValid(): boolean {
        return true;
    }
}
