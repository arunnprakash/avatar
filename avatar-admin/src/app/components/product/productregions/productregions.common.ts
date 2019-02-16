import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { ProductRegionsComponent } from './productregions/productregions.component';
import { ProductRegionDetailComponent } from './productregion-detail/productregion-detail.component';

export const componentDeclarations: any[] = [ProductRegionsComponent, ProductRegionDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'productregions',
                                   component: ProductRegionsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'productregion',
                                   component: ProductRegionDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
