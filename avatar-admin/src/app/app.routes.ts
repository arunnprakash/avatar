import { Routes } from '@angular/router';

import { NeedAuthGuard } from './services/needauthguard';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from "./components/home/home.component";

import { LanguagesComponent } from "./components/master/languages/languages/languages.component";
import { LanguageDetailComponent } from "./components/master/languages/language-detail/language-detail.component";
import { VillagesComponent } from "./components/master/villages/villages/villages.component";
import { VillageDetailComponent } from "./components/master/villages/village-detail/village-detail.component";
import { TaluksComponent } from "./components/master/taluks/taluks/taluks.component";
import { TalukDetailComponent } from "./components/master/taluks/taluk-detail/taluk-detail.component";
import { DistrictsComponent } from "./components/master/districts/districts/districts.component";
import { DistrictDetailComponent } from "./components/master/districts/district-detail/district-detail.component";
import { StatesComponent } from "./components/master/states/states/states.component";
import { StateDetailComponent } from "./components/master/states/state-detail/state-detail.component";
import { WareHousesComponent } from './components/master/warehouses/warehouses/warehouses.component';
import { WareHouseDetailComponent } from './components/master/warehouses/warehouse-detail/warehouse-detail.component';
import { MarketsComponent } from './components/master/market/markets/markets.component';
import { MarketDetailComponent } from './components/master/market/market-detail/market-detail.component';

import { UsersComponent } from "./components/authorization/users/users/users.component";
import { UserDetailComponent } from "./components/authorization/users/user-detail/user-detail.component";
import { RolesComponent } from "./components/authorization/roles/roles/roles.component";
import { RoleDetailComponent } from "./components/authorization/roles/role-detail/role-detail.component";
import { QcWareHouseMappingsComponent } from './components/authorization/qc-warehouse-mappings/qc-warehouse-mappings/qc-warehouse-mappings.component';
import { QcWareHouseMappingDetailComponent } from './components/authorization/qc-warehouse-mappings/qc-warehouse-mapping-detail/qc-warehouse-mapping-detail.component';
import { TruckDriverWareHouseMappingsComponent } from './components/authorization/truckdriver-warehouse-mappings/truckdriver-warehouse-mappings/truckdriver-warehouse-mappings.component';
import { TruckDriverWareHouseMappingDetailComponent } from './components/authorization/truckdriver-warehouse-mappings/truckdriver-warehouse-mapping-detail/truckdriver-warehouse-mapping-detail.component';
import { UsersAssetTypesComponent } from "./components/authorization/assetTypes/assetTypes/assetTypes.component";
import { UsersAssetTypeDetailComponent } from "./components/authorization/assetTypes/assetType-detail/assetType-detail.component";
import { ProductsComponent } from "./components/product/products/products/products.component";
import { ProductDetailComponent } from "./components/product/products/product-detail/product-detail.component";
import { ProductsAssetTypesComponent } from "./components/product/assetTypes/assetTypes/assetTypes.component";
import { ProductsAssetTypeDetailComponent } from "./components/product/assetTypes/assetType-detail/assetType-detail.component";
import { PricesComponent } from "./components/product/prices/prices/prices.component";
import { PriceDetailComponent } from "./components/product/prices/price-detail/price-detail.component";
import { MarketPricesComponent } from "./components/product/market-prices/market-prices/market-prices.component";
import { MarketPriceDetailComponent } from "./components/product/market-prices/market-price-detail/market-price-detail.component";
import { HolidaysComponent } from "./components/product/holidays/holidays/holidays.component";
import { HolidayDetailComponent } from "./components/product/holidays/holiday-detail/holiday-detail.component";
import { ProductRegionsComponent } from "./components/product/productregions/productregions/productregions.component";
import { ProductRegionDetailComponent } from "./components/product/productregions/productregion-detail/productregion-detail.component";

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
                       path: 'warehouses',
                       component: WareHousesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'warehouse',
                       component: WareHouseDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'markets',
                       component: MarketsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'market',
                       component: MarketDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'qc-warehouse-mappings',
                       component: QcWareHouseMappingsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'qc-warehouse-mapping',
                       component: QcWareHouseMappingDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'truckdriver-warehouse-mappings',
                       component: TruckDriverWareHouseMappingsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'truckdriver-warehouse-mapping',
                       component: TruckDriverWareHouseMappingDetailComponent,
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
                   },
                   {
                       path: 'market-prices',
                       component: MarketPricesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'market-price',
                       component: MarketPriceDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'holidays',
                       component: HolidaysComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'holiday',
                       component: HolidayDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'productregions',
                       component: ProductRegionsComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'productregion',
                       component: ProductRegionDetailComponent,
                       outlet: 'menuRouterOutlet'
                   }
       ]
    }
];
