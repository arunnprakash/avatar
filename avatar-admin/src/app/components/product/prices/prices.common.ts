import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { PricesComponent } from './prices/prices.component';
import { PriceDetailComponent } from './price-detail/price-detail.component';

export const componentDeclarations: any[] = [PricesComponent, PriceDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'prices',
                                   component: PricesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'price',
                                   component: PriceDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
