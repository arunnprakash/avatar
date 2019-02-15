import { Routes } from '@angular/router';

import { NeedAuthGuard } from './services/needauthguard';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from "./components/home/home.component";
import { UsersComponent } from "./components/authorization/users/users/users.component";
import { UserDetailComponent } from "./components/authorization/users/user-detail/user-detail.component";
import { RolesComponent } from "./components/authorization/roles/roles/roles.component";
import { RoleDetailComponent } from "./components/authorization/roles/role-detail/role-detail.component";
import { VillagesComponent } from "./components/authorization/villages/villages/villages.component";
import { VillageDetailComponent } from "./components/authorization/villages/village-detail/village-detail.component";
import { TaluksComponent } from "./components/authorization/taluks/taluks/taluks.component";
import { TalukDetailComponent } from "./components/authorization/taluks/taluk-detail/taluk-detail.component";
import { DistrictsComponent } from "./components/authorization/districts/districts/districts.component";
import { DistrictDetailComponent } from "./components/authorization/districts/district-detail/district-detail.component";
import { StatesComponent } from "./components/authorization/states/states/states.component";
import { StateDetailComponent } from "./components/authorization/states/state-detail/state-detail.component";
import { LanguagesComponent } from "./components/authorization/languages/languages/languages.component";
import { LanguageDetailComponent } from "./components/authorization/languages/language-detail/language-detail.component";
import { UsersAssetTypesComponent } from "./components/authorization/assetTypes/assetTypes/assetTypes.component";
import { UsersAssetTypeDetailComponent } from "./components/authorization/assetTypes/assetType-detail/assetType-detail.component";
import { ProductsComponent } from "./components/product/products/products/products.component";
import { ProductDetailComponent } from "./components/product/products/product-detail/product-detail.component";
import { ProductsAssetTypesComponent } from "./components/product/assetTypes/assetTypes/assetTypes.component";
import { ProductsAssetTypeDetailComponent } from "./components/product/assetTypes/assetType-detail/assetType-detail.component";
import { PricesComponent } from "./components/product/prices/prices/prices.component";
import { PriceDetailComponent } from "./components/product/prices/price-detail/price-detail.component";

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full',
    },
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [ NeedAuthGuard ],
        children: [
                   {
                       path: 'users',
                       component: UsersComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'user',
                       component: UserDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'roles',
                       component: RolesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'role',
                       component: RoleDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'villages',
                       component: VillagesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'village',
                       component: VillageDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'taluks',
                       component: TaluksComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'taluk',
                       component: TalukDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'districts',
                       component: DistrictsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'district',
                       component: DistrictDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'states',
                       component: StatesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'state',
                       component: StateDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'languages',
                       component: LanguagesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'language',
                       component: LanguageDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'usersAssetTypes',
                       component: UsersAssetTypesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'usersAssetType',
                       component: UsersAssetTypeDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'products',
                       component: ProductsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'product',
                       component: ProductDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'productsAssetTypes',
                       component: ProductsAssetTypesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'productsAssetType',
                       component: ProductsAssetTypeDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'dashboard',
                       component: DashboardComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'prices',
                       component: PricesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'price',
                       component: PriceDetailComponent,
                       outlet: 'menuRouterOutlet'
                   }
       ]
    }
];
