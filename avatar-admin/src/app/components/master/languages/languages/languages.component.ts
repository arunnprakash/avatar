import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { SlicePipe } from '@angular/common';
import { ConfirmationService, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { baseTemplate } from '../../../base/base.template';
import { baseCss } from '../../../base/base.css';
import { BaseComponent } from '../../../base/base.component';

import { LanguageService } from "../../../../services/master/languageservice.generated";
import { AuthService } from "../../../../services/auth.service";
import { LanguageDTO } from "../../../../services/master/languagedto.model";
import { LanguageDetailComponent } from "../language-detail/language-detail.component";

import * as _ from "lodash";

@Component( {
    selector: 'languages',
    template: baseTemplate,
    styles: [baseCss],
    providers: [ConfirmationService, DialogService]
} )
export class LanguagesComponent extends BaseComponent implements OnInit {

    protected title = 'Language';
    protected localCols: any[] = [{ field: 'languageName', header: 'LanguageName', dataType: 'INPUT' }];

    constructor( languageService: LanguageService, authService: AuthService, translate: TranslateService, domSanitizer: DomSanitizer,
            confirmationService: ConfirmationService, dialogService: DialogService, 
            router: Router, activatedRoute: ActivatedRoute, vcRef: ViewContainerRef) {
        super( languageService, authService, translate, domSanitizer, confirmationService, dialogService, LanguageDetailComponent, router, activatedRoute, vcRef);
    }
    ngOnInit() {
        super.ngOnInit();
    }
    protected initEmptyModel() {
        this.model = new LanguageDTO();
    }
}
