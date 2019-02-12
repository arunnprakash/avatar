import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { AssetTypesComponent } from './assetTypes/assetTypes.component';
import { AssetTypeDetailComponent } from './assetType-detail/assetType-detail.component';

export const componentDeclarations: any[] = [AssetTypesComponent, AssetTypeDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'assetTypes',
                                   component: AssetTypesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'assetType',
                                   component: AssetTypeDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
