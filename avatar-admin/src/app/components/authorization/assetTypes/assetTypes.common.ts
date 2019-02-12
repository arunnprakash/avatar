import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { UsersAssetTypesComponent } from './assetTypes/assetTypes.component';
import { UsersAssetTypeDetailComponent } from './assetType-detail/assetType-detail.component';

export const componentDeclarations: any[] = [UsersAssetTypesComponent, UsersAssetTypeDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'usersAssetTypes',
                                   component: UsersAssetTypesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'usersAssetType',
                                   component: UsersAssetTypeDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
