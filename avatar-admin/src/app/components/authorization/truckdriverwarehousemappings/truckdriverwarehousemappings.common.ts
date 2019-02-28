import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { TruckDriverWareHouseMappingsComponent } from './truckdriverwarehousemappings/truckdriverwarehousemappings.component';
import { TruckDriverWareHouseMappingDetailComponent } from './truckdriverwarehousemapping-detail/truckdriverwarehousemapping-detail.component';

export const componentDeclarations: any[] = [TruckDriverWareHouseMappingsComponent, TruckDriverWareHouseMappingDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'truckdriverwarehousemappings',
                                   component: TruckDriverWareHouseMappingsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'truckdriverwarehousemapping',
                                   component: TruckDriverWareHouseMappingDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
