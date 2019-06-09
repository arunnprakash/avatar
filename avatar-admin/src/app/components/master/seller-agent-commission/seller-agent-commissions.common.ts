import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { SellerAgentCommissionsComponent } from './seller-agent-commission/seller-agent-commissions.component';
import { SellerAgentCommissionDetailComponent } from './seller-agent-commission-detail/seller-agent-commission-detail.component';

export const componentDeclarations: any[] = [SellerAgentCommissionsComponent, SellerAgentCommissionDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'seller-agent-commissions',
                                   component: SellerAgentCommissionsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'seller-agent-commission',
                                   component: SellerAgentCommissionDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
