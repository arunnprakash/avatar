import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { LanguagesComponent } from './languages/languages.component';
import { LanguageDetailComponent } from './language-detail/language-detail.component';

export const componentDeclarations: any[] = [LanguagesComponent, LanguageDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'languages',
                                   component: LanguagesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'language',
                                   component: LanguageDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
