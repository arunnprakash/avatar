import { Component, OnInit, AfterViewInit , ViewContainerRef, ViewChildren, QueryList } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import { TokenModel, RadAutoCompleteTextView } from "nativescript-ui-autocomplete";
import { RadAutoCompleteTextViewComponent  } from "nativescript-ui-autocomplete/angular";

import * as _ from "lodash";

@Component({
  selector: 'users',
  template: baseDataViewTemplate,
  styles: [baseCss],
  providers: [ModalDialogService]
})
export class UsersComponent extends BaseComponent implements OnInit, AfterViewInit  {

    tags: string[]=["Test"];
    protected title = 'User';
    
    protected localCols: any[] = [
         { field: 'userName', header: 'UserName', dataType: 'INPUT', autocapitalizationType: 'none', keyboardType: 'email' },
         { field: 'mobileNumber', header: 'MobileNumber', dataType: 'INPUT', autocapitalizationType:'none', keyboardType: 'phone' },
         { field: 'firstName', header: 'FirstName', dataType: 'INPUT', autocapitalizationType:'none', keyboardType: 'email' },
         { field: 'lastName', header: 'LastName', dataType: 'INPUT', autocapitalizationType:'none', keyboardType: 'email' },
         { field: 'dob', header: 'DateOfBirth', dataType: 'DATE' },
         { field: 'roles', header: 'Roles', dataType: 'AUTOCOMPLETE', multiple: true, options: new ObservableArray<TokenModel>(), optionLabel:"roleName" },
         { field: 'preferredLanguage', header: 'PreferredLanguage', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"languageName"},
         { field: 'village', header: 'Village', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
         { field: 'gender', header: 'Gender', dataType: 'AUTOCOMPLETE', multiple: false, options: new ObservableArray<TokenModel>() , optionLabel:"en"},
         { field: 'assets', header: 'Assets', dataType: 'FILE', multiple: true, options: [] , optionLabel:"assetValue"}
    ];
    constructor( userService: UserService, authService: AuthService, translate: TranslateService, 
            private roleService: RoleService, private languageService: LanguageService, 
            private villageService: VillageService,  private genderService: GenderService,
            private assetTypeService: AssetTypeService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( userService, authService, translate, modalDialogService, dialogService, UserDetailComponent, router, activatedRoute, vcRef);
        this.languageCode = authService.getUserInfo().preferredLanguage.languageCode;
    }
    ngAfterViewInit() {
        
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit user.component.tns");
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
            menuItem.originalOptions = genders;
            genders.forEach( (gender: GenderDTO ) => {
                menuItem.options.push(new TokenModel(gender[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Gender List');
        });
    }
    initVillageList() {
        this.villageService.getAllExceptDeleted().subscribe((villages: VillageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'village' });
            menuItem.optionLabel = this.languageCode;
            menuItem.originalOptions = villages;
            villages.forEach( (village: VillageDTO ) => {
                menuItem.options.push(new TokenModel(village[this.languageCode], null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Village List');
        });
    }

    initLanguageList() {
        this.languageService.getAllExceptDeleted().subscribe((languages: LanguageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'preferredLanguage' });
            menuItem.originalOptions = languages;
            languages.forEach( (language: LanguageDTO ) => {
                menuItem.options.push(new TokenModel(language.languageName, null));
            });
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Language List');
        });
    }
    initRolesList() {
        this.roleService.getAllExceptDeleted().subscribe((roles: RoleDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'roles' });
            menuItem.originalOptions = roles;
            roles.forEach( (role: RoleDTO ) => {
                menuItem.options.push(new TokenModel(role.roleName, null));
            });
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
