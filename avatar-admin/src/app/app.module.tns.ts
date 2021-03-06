import { NgModule, NgModuleFactoryLoader, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptModule } from 'nativescript-angular/nativescript.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptFormsModule } from 'nativescript-angular/forms';
import { NativeScriptHttpClientModule } from 'nativescript-angular/http-client';

import { Http } from '@angular/http';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { HTTP_INTERCEPTORS } from '@angular/common/http';

//import ngx-translate and the http loader
import {TranslateService, TranslateLoader, TranslateModule, TranslatePipe} from '@ngx-translate/core';
//import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {NSNgxTranslateLoader} from './ns.ngx.translate.loader.tns';

/*UI Modules*/
import { NativeScriptUISideDrawerModule } from "nativescript-ui-sidedrawer/angular";
import { NativeScriptUIAutoCompleteTextViewModule } from "nativescript-ui-autocomplete/angular";
import { TNSCheckBoxModule } from 'nativescript-checkbox/angular';

import * as localStorage from 'nativescript-localstorage';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';

import { CoreModule } from './components/core/core.module';
import { DashboardModule } from './components/dashboard/dashboard.module';

import { UsersModule } from './components/authorization/users/users.module';
import { RolesModule } from './components/authorization/roles/roles.module';
import { QcWareHouseMappingsModule } from './components/authorization/qc-warehouse-mappings/qc-warehouse-mappings.module';
import { TruckDriverWareHouseMappingsModule } from './components/authorization/truckdriver-warehouse-mappings/truckdriver-warehouse-mappings.module';
import { SellerAgentWareHouseMappingsModule } from './components/authorization/selleragent-warehouse-mappings/selleragent-warehouse-mappings.module';
import { UsersAssetTypesModule } from './components/authorization/assetTypes/assetTypes.module';

import { VillagesModule } from './components/master/villages/villages.module';
import { TaluksModule } from './components/master/taluks/taluks.module';
import { DistrictsModule } from './components/master/districts/districts.module';
import { StatesModule } from './components/master/states/states.module';
import { WareHousesModule } from './components/master/warehouses/warehouses.module';
import { LanguagesModule } from './components/master/languages/languages.module';
import { MarketsModule } from './components/master/market/markets.module';
import { SellerMerchantCommissionsModule } from './components/master/seller-merchant-commission/seller-merchant-commissions.module';
import { SellerAgentCommissionsModule } from './components/master/seller-agent-commission/seller-agent-commissions.module';
import { SellerTransportationChargesModule } from './components/master/seller-transportation-charge/seller-transportation-charges.module';

import { ProductsAssetTypesModule } from './components/product/assetTypes/assetTypes.module';
import { ProductsModule } from './components/product/products/products.module';
import { MarketPricesModule } from './components/product/market-prices/market-prices.module';
import { PricesModule } from './components/product/prices/prices.module';
import { HolidaysModule } from './components/product/holidays/holidays.module';
import { ProductRegionsModule } from './components/product/productregions/productregions.module';

import { ApiUrls } from './api-settings/api-urls';
import { AuthService } from "./services/auth.service";
import { TokenInterceptor } from "./services/token.interceptor";
import { NeedAuthGuard } from "./services/needauthguard";
import { MasterAPIModule } from "./services/master/api.module";
import { AuthorizationAPIModule } from "./services/authorization/api.module";
import { ProductAPIModule } from "./services/product/api.module";
import { TransactionAPIModule } from "./services/transaction/api.module";

import { registerElement } from "nativescript-angular/element-registry";
registerElement("Mapbox", () => require("nativescript-mapbox").MapboxView);

//required for AOT compilation
/*export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}*/
/*export function createTranslateLoader(http: Http) {
    return new TranslateHttpLoader(<any>http, '/assets/i18n/', '.json');
}*/
export function createTranslateLoader() {
    return new NSNgxTranslateLoader("./assets/i18n/", ".json");
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    HomeComponent
  ],
  imports: [
    NativeScriptModule,
    NativeScriptCommonModule,
    NativeScriptFormsModule,
    NativeScriptHttpClientModule,
    TranslateModule.forRoot({
        loader: {
            provide: TranslateLoader,
            useFactory: createTranslateLoader
        }
    }),
    MasterAPIModule.forRoot({context: ApiUrls.masterServiceApiBaseUrl}),
    AuthorizationAPIModule.forRoot({context: ApiUrls.authorizationServiceApiBaseUrl}),
    ProductAPIModule.forRoot({context: ApiUrls.productServiceApiBaseUrl}),
    TransactionAPIModule.forRoot({context: ApiUrls.transactionServiceApiBaseUrl}),
    AppRoutingModule,
    
    CoreModule,

    LanguagesModule,
    VillagesModule,
    TaluksModule,
    DistrictsModule,
    StatesModule,
    WareHousesModule,
    MarketsModule,
    SellerMerchantCommissionsModule,
    SellerAgentCommissionsModule,
    SellerTransportationChargesModule,

    DashboardModule,
    UsersModule,
    RolesModule,
    QcWareHouseMappingsModule,
    TruckDriverWareHouseMappingsModule,
    SellerAgentWareHouseMappingsModule,
    UsersAssetTypesModule,
    
    ProductsAssetTypesModule,
    ProductsModule,
    MarketPricesModule,
    PricesModule,
    HolidaysModule,
    ProductRegionsModule,
    
    NativeScriptUISideDrawerModule,
    NativeScriptUIAutoCompleteTextViewModule,
    TNSCheckBoxModule
  ],
  providers: [    
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: Storage, useValue: localStorage},
    /*{ provide: NgModuleFactoryLoader, useClass: NSModuleFactoryLoader },*/
    NeedAuthGuard,
    AuthService,
    TranslateService],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
