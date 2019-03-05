import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { TruckDriverWareHouseMappingsComponent } from './truckdriver-warehouse-mappings/truckdriver-warehouse-mappings.component';
import { TruckDriverWareHouseMappingDetailComponent } from './truckdriver-warehouse-mapping-detail/truckdriver-warehouse-mapping-detail.component';

export const componentDeclarations: any[] = [TruckDriverWareHouseMappingsComponent, TruckDriverWareHouseMappingDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'truckdriver-warehouse-mappings',
                                   component: TruckDriverWareHouseMappingsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'truckdriver-warehouse-mapping',
                                   component: TruckDriverWareHouseMappingDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
