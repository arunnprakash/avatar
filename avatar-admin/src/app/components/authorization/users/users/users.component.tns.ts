import { Component, OnInit, AfterViewInit , ViewContainerRef, ViewChildren, QueryList } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { baseDataViewTemplate } from '../../../base/base.dataView.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { UserService } from '../../../../services/authorization/userservice.generated';
import { RoleService } from '../../../../services/authorization/roleservice.generated';
import { LanguageService } from '../../../../services/authorization/languageservice.generated';
import { VillageService } from '../../../../services/authorization/villageservice.generated';
import { AuthService } from "../../../../services/auth.service";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { RoleDTO } from "../../../../services/authorization/roledto.model";
import { LanguageDTO } from "../../../../services/authorization/languagedto.model";
import { VillageDTO } from "../../../../services/authorization/villagedto.model";
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
    languageCode: string;
    protected localCols: any[] = [
         { field: 'userName', header: 'UserName', dataType: 'INPUT' },
         { field: 'mobileNumber', header: 'MobileNumber', dataType: 'INPUT' },
         { field: 'firstName', header: 'FirstName', dataType: 'INPUT' },
         { field: 'lastName', header: 'LastName', dataType: 'INPUT' },
         { field: 'dob', header: 'DateOfBirth', dataType: 'DATE' },
         { field: 'roles', header: 'Roles', dataType: 'AUTOCOMPLETE', options: new ObservableArray<TokenModel>(), optionLabel:"roleName" },
         { field: 'preferredLanguage', header: 'PreferredLanguage', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"languageName"},
         { field: 'village', header: 'Village', dataType: 'AUTOCOMPLETE', multiple: false, options: [] , optionLabel:"en"}
    ];
    constructor( userService: UserService, authService: AuthService, private roleService: RoleService,
            private languageService: LanguageService, private villageService: VillageService,
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef ) {
        super( userService, authService, modalDialogService, dialogService, UserDetailComponent, router, activatedRoute, vcRef );
    }
    ngAfterViewInit() {
        
    }
    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit user.component.tns");
        this.languageCode = "en";//this.authService.getUserInfo().preferredLanguage.languageCode;
        this.initRolesList();
        this.initVillageList();
    }
    initVillageList() {
        this.showLoading(true);
        this.villageService.getAllExceptDeleted().subscribe((villages: VillageDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'village' });
            villages.forEach( (village: VillageDTO ) => {
                //menuItem.options.push(new TokenModel(village[this.languageCode], null));
            });
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Village List');
        });
    }
    onLoaded(event) { 
        let autoComplete: RadAutoCompleteTextView = <RadAutoCompleteTextView>event.object;
        this.initAutoComplete(autoComplete);
    }
    initAutoComplete(autoComplete: RadAutoCompleteTextView) {
        setTimeout(()=>{
            autoComplete.readOnly = true;
            autoComplete.showCloseButton = false;
            this.recordList.forEach((userDTO: any) => {
                if (userDTO.id == autoComplete.id) {
                    userDTO.roles.forEach( (role: RoleDTO ) => {
                        autoComplete.addToken(new TokenModel(role.roleName, null));
                    });
                }
                
            });
       }, 3000)
    }

    initRolesList() {
        this.showLoading(true);
        this.roleService.getAllExceptDeleted().subscribe((roles: RoleDTO[]) => {
            let menuItem: any = _.find(this.localCols, { 'field': 'roles' });
            roles.forEach( (role: RoleDTO ) => {
                menuItem.options.push(new TokenModel(role.roleName, null));
            });
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
