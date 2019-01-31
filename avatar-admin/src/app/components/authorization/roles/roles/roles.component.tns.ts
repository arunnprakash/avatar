import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { RoleService } from "../../../../services/authorization/roleservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { RoleDTO } from "../../../../services/authorization/roledto.model";
import { RoleDetailComponent } from "../role-detail/role-detail.component";

@Component( {
    selector: 'roles',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class RolesComponent extends BaseComponent implements OnInit {

    protected title = 'Role';
    protected localCols: any[] = [{ field: 'roleName', header: 'RoleName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( roleService: RoleService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( roleService, authService, translate, modalDialogService, dialogService, RoleDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }

    protected initEmptyModel() {
        this.model = new RoleDTO();
    }
}
