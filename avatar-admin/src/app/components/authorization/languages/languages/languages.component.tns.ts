import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { LanguageService } from "../../../../services/authorization/languageservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { LanguageDTO } from "../../../../services/authorization/languagedto.model";
import { LanguageDetailComponent } from "../language-detail/language-detail.component";

@Component( {
    selector: 'languages',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ModalDialogService]
} )
export class LanguagesComponent extends BaseComponent implements OnInit {

    protected title = 'Language';
    protected localCols: any[] = [{ field: 'languageName', header: 'LanguageName', dataType: 'INPUT', autocapitalizationType:'allcharacters', keyboardType: 'email' }];

    constructor( languageService: LanguageService, authService: AuthService, translate: TranslateService, 
            modalDialogService: ModalDialogService, dialogService: ModalDialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( languageService, authService, translate, modalDialogService, dialogService, LanguageDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }

    protected initEmptyModel() {
        this.model = new LanguageDTO();
    }
}
