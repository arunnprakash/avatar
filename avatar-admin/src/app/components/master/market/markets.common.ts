import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { MarketsComponent } from './markets/markets.component';
import { MarketDetailComponent } from './market-detail/market-detail.component';

export const componentDeclarations: any[] = [MarketsComponent, MarketDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'markets',
                                   component: MarketsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'market',
                                   component: MarketDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
