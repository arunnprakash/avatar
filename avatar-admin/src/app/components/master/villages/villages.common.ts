import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { VillagesComponent } from './villages/villages.component';
import { VillageDetailComponent } from './village-detail/village-detail.component';

export const componentDeclarations: any[] = [VillagesComponent, VillageDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'villages',
                                   component: VillagesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'village',
                                   component: VillageDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
