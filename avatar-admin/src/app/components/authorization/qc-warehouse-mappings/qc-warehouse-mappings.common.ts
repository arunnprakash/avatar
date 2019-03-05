import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { QcWareHouseMappingsComponent } from './qc-warehouse-mappings/qc-warehouse-mappings.component';
import { QcWareHouseMappingDetailComponent } from './qc-warehouse-mapping-detail/qc-warehouse-mapping-detail.component';

export const componentDeclarations: any[] = [QcWareHouseMappingsComponent, QcWareHouseMappingDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'qc-warehouse-mappings',
                                   component: QcWareHouseMappingsComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'qc-warehouse-mapping',
                                   component: QcWareHouseMappingDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
