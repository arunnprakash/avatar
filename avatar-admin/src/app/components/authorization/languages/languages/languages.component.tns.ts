import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Page } from "tns-core-modules/ui/page";
import { SlicePipe } from '@angular/common';
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { LanguageService } from "../../../../services/master/languageservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { LanguageDTO } from "../../../../services/master/languagedto.model";
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
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef, private page: Page) {
        super( languageService, authService, translate, modalDialogService, dialogService, LanguageDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
        this.page.actionBarHidden = true;
        console.log("ngOnInit language.component.tns");
    }

    protected initEmptyModel() {
        this.model = new LanguageDTO();
    }
}
