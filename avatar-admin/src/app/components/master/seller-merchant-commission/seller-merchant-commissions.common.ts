import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { SellerMerchantCommissionsComponent } from './seller-merchant-commission/seller-merchant-commissions.component';
import { SellerMerchantCommissionDetailComponent } from './seller-merchant-commission-detail/seller-merchant-commission-detail.component';

export const componentDeclarations: any[] = [SellerMerchantCommissionsComponent, SellerMerchantCommissionDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'seller-merchant-commissions',
                                   component: SellerMerchantCommissionsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'seller-merchant-commission',
                                   component: SellerMerchantCommissionDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
