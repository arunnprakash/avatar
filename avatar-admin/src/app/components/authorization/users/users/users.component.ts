import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { ConfirmationService, DialogService } from 'primeng/api';
import { baseDataViewTemplate } from '../../../base/base.dataView.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { UserService } from '../../../../services/authorization/userservice.generated';
import { RoleService } from '../../../../services/authorization/roleservice.generated';
import { LanguageService } from '../../../../services/authorization/languageservice.generated';
import { AuthService } from "../../../../services/auth.service";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { RoleDTO } from "../../../../services/authorization/roledto.model";
import { LanguageDTO } from "../../../../services/authorization/languagedto.model";
import { UserDetailComponent } from "../user-detail/user-detail.component";

import * as _ from "lodash";

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
         { field: 'lastName', header: 'LastName', dataType: 'INPUT' },
         { field: 'dob', header: 'DateOfBirth', dataType: 'DATE' },
         { field: 'roles', header: 'Roles', dataType: 'AUTOCOMPLETE', multiple: true, options: [] , optionLabel:"roleName"},
         { field: 'preferredLanguage', header: 'PreferredLanguage', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"languageName"}
    ];
    constructor( userService: UserService, authService: AuthService, private roleService: RoleService, private languageService: LanguageService,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef ) {
        super( userService, authService, confirmationService, dialogService,UserDetailComponent, router, activatedRoute, vcRef );
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit user.component");
        this.initRolesList();
        this.initLanguageList();
    }
    initLanguageList() {
        this.showLoading(true);
        this.languageService.getAllExceptDeleted().subscribe((languages: LanguageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'preferredLanguage' });
            menuItem.options = languages;
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Language List');
        });
    }
    initRolesList() {
        this.showLoading(true);
        this.roleService.getAllExceptDeleted().subscribe((roles: RoleDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'roles' });
            menuItem.options = roles;
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Roles List');
        });
    }
    protected initEmptyModel() {
        this.model = new UserDTO();
    }

    protected isModelValid(): boolean {
        return true;
    }
}
