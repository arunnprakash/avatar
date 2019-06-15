import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { SellerAgentWareHouseMappingsComponent } from './selleragent-warehouse-mappings/selleragent-warehouse-mappings.component';
import { SellerAgentWareHouseMappingDetailComponent } from './selleragent-warehouse-mapping-detail/selleragent-warehouse-mapping-detail.component';

export const componentDeclarations: any[] = [SellerAgentWareHouseMappingsComponent, SellerAgentWareHouseMappingDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'selleragent-warehouse-mappings',
                                   component: SellerAgentWareHouseMappingsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'selleragent-warehouse-mapping',
                                   component: SellerAgentWareHouseMappingDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
