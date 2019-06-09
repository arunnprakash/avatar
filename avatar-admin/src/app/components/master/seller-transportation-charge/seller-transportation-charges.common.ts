import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { SellerTransportationChargesComponent } from './seller-transportation-charge/seller-transportation-charges.component';
import { SellerTransportationChargeDetailComponent } from './seller-transportation-charge-detail/seller-transportation-charge-detail.component';

export const componentDeclarations: any[] = [SellerTransportationChargesComponent, SellerTransportationChargeDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'seller-transportation-charges',
                                   component: SellerTransportationChargesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'seller-transportation-charge',
                                   component: SellerTransportationChargeDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
