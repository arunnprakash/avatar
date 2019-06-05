import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { DistrictsComponent } from './districts/districts.component';
import { DistrictDetailComponent } from './district-detail/district-detail.component';

export const componentDeclarations: any[] = [DistrictsComponent, DistrictDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'districts',
                                   component: DistrictsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'district',
                                   component: DistrictDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
