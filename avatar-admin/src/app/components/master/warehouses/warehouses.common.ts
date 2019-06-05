import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { WareHousesComponent } from './warehouses/warehouses.component';
import { WareHouseDetailComponent } from './warehouse-detail/warehouse-detail.component';

export const componentDeclarations: any[] = [WareHousesComponent, WareHouseDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'warehouses',
                                   component: WareHousesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'warehouse',
                                   component: WareHouseDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
