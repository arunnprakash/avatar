import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";
import { baseDataViewTemplate } from '../../../base/base.dataView.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { UserService } from '../../../../services/authorization/userservice.generated';
import { RoleService } from '../../../../services/authorization/roleservice.generated';

import { LanguageService } from '../../../../services/master/languageservice.generated';
import { VillageService } from '../../../../services/master/villageservice.generated';
import { GenderService } from '../../../../services/master/genderservice.generated';
import { AssetTypeService } from "../../../../services/master/assettypeservice.generated";
import { AuthService } from "../../../../services/auth.service";

import { UserDTO } from "../../../../services/authorization/userdto.model";
import { RoleDTO } from "../../../../services/authorization/roledto.model";
import { LanguageDTO } from "../../../../services/master/languagedto.model";
import { VillageDTO } from "../../../../services/master/villagedto.model";
import { GenderDTO } from "../../../../services/master/genderdto.model";
import { AssetTypeDTO } from "../../../../services/master/assettypedto.model";
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
         { field: 'preferredLanguage', header: 'PreferredLanguage', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"languageName"},
         { field: 'village', header: 'Village', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
         { field: 'gender', header: 'Gender', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"},
         { field: 'assets', header: 'Assets', dataType: 'FILE', multiple: true, options: [] , optionLabel:"assetValue"}
    ];
    constructor( userService: UserService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            private roleService: RoleService, private languageService: LanguageService, 
            private villageService: VillageService, private genderService: GenderService,
            private assetTypeService: AssetTypeService,
            confirmationService: ConfirmationService, dialogService: DialogService,
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef ) {
        super( userService, authService, translate, domSanitizer, confirmationService, dialogService, UserDetailComponent, router, activatedRoute, vcRef );
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit user.component");
        this.initFieldsLabel("users");
        this.initRolesList();
        this.initAssetTypeList();
        this.initLanguageList();
        this.initVillageList();
        this.initGenderList();
    }
    initAssetTypeList() {
        this.assetTypeService.getAllExceptDeleted().subscribe((assetTypes: AssetTypeDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'assets' });
            menuItem.options = assetTypes;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Asset Type List');
        });
    }
    initGenderList() {
        this.genderService.getAllExceptDeleted().subscribe((genders: GenderDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'gender' });
            menuItem.optionLabel = this.languageCode;
            menuItem.options = genders;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Gender List');
        });
    }
    initVillageList() {
        this.villageService.getAllExceptDeleted().subscribe((villages: VillageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'village' });
            menuItem.optionLabel = this.languageCode;
            menuItem.options = villages;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Village List');
        });
    }
    initLanguageList() {
        this.languageService.getAllExceptDeleted().subscribe((languages: LanguageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'preferredLanguage' });
            menuItem.options = languages;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Language List');
        });
    }
    initRolesList() {
        this.roleService.getAllExceptDeleted().subscribe((roles: RoleDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'roles' });
            menuItem.options = roles;
        },
        ( error ) => {
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
