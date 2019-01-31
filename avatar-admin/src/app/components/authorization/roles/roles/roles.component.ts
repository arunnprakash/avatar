import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
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
    providers: [ConfirmationService, DialogService]
} )
export class RolesComponent extends BaseComponent implements OnInit {

    protected title = 'Role';
    protected localCols: any[] = [{ field: 'roleName', header: 'RoleName', dataType: 'INPUT' }];

    constructor( roleService: RoleService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( roleService, authService, translate, domSanitizer, confirmationService, dialogService, RoleDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }

    protected initEmptyModel() {
        this.model = new RoleDTO();
    }
}
