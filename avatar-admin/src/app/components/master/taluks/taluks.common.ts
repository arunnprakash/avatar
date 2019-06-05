import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { TaluksComponent } from './taluks/taluks.component';
import { TalukDetailComponent } from './taluk-detail/taluk-detail.component';

export const componentDeclarations: any[] = [TaluksComponent, TalukDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'taluks',
                                   component: TaluksComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'taluk',
                                   component: TalukDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
