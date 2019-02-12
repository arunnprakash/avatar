import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { ProductsAssetTypesComponent } from './assetTypes/assetTypes.component';
import { ProductsAssetTypeDetailComponent } from './assetType-detail/assetType-detail.component';

export const componentDeclarations: any[] = [ProductsAssetTypesComponent, ProductsAssetTypeDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'productsAssetTypes',
                                   component: ProductsAssetTypesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'productsAssetType',
                                   component: ProductsAssetTypeDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
