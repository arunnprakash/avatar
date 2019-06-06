import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { MarketPricesComponent } from './market-prices/market-prices.component';
import { MarketPriceDetailComponent } from './market-price-detail/market-price-detail.component';

export const componentDeclarations: any[] = [MarketPricesComponent, MarketPriceDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'market-prices',
                                   component: MarketPricesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'market-price',
                                   component: MarketPriceDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
