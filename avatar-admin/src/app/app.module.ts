import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

//import ngx-translate and the http loader
import {TranslateService, TranslateLoader, TranslateModule, TranslatePipe} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { MenuModule } from 'primeng/menu';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { DialogModule } from 'primeng/dialog';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { PasswordModule } from 'primeng/password';
import { CardModule } from 'primeng/card';
import { BlockUIModule } from 'primeng/blockui';
import { DataViewModule } from 'primeng/dataview';

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
import { LanguagesModule } from './components/authorization/languages/languages.module';
import { VillagesModule } from './components/authorization/villages/villages.module';
import { TaluksModule } from './components/authorization/taluks/taluks.module';
import { DistrictsModule } from './components/authorization/districts/districts.module';
import { StatesModule } from './components/authorization/states/states.module';
import { WareHousesModule } from './components/authorization/warehouses/warehouses.module';
import { QcWareHouseMappingsModule } from './components/authorization/qc-warehouse-mappings/qc-warehouse-mappings.module';
import { TruckDriverWareHouseMappingsModule } from './components/authorization/truckdriver-warehouse-mappings/truckdriver-warehouse-mappings.module';
import { UsersAssetTypesModule } from './components/authorization/assetTypes/assetTypes.module';

import { ProductsAssetTypesModule } from './components/product/assetTypes/assetTypes.module';
import { ProductsModule } from './components/product/products/products.module';
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

//required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
        loader: {
            provide: TranslateLoader,
            useFactory: HttpLoaderFactory,
            deps: [HttpClient]
        }
    }),
    MasterAPIModule.forRoot({context: ApiUrls.masterServiceApiBaseUrl}),
    AuthorizationAPIModule.forRoot({context: ApiUrls.authorizationServiceApiBaseUrl}),
    ProductAPIModule.forRoot({context: ApiUrls.productServiceApiBaseUrl}),
    TransactionAPIModule.forRoot({context: ApiUrls.transactionServiceApiBaseUrl}),
    AppRoutingModule,
    
    CoreModule,
    
    DashboardModule,
    UsersModule,
    RolesModule,
    LanguagesModule,
    VillagesModule,
    TaluksModule,
    DistrictsModule,
    StatesModule,
    WareHousesModule,
    QcWareHouseMappingsModule,
    TruckDriverWareHouseMappingsModule,
    UsersAssetTypesModule,
    
    ProductsAssetTypesModule,
    ProductsModule,
    PricesModule,
    HolidaysModule,
    ProductRegionsModule,
    
    CalendarModule,
    InputTextModule,
    ButtonModule,
    TableModule,
    MenuModule,
    MessagesModule,
    MessageModule,
    DialogModule,
    ToastModule,
    DynamicDialogModule,
    PanelModule,
    DropdownModule,
    MultiSelectModule,
    ScrollPanelModule,
    CardModule,
    PasswordModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: Storage, useValue: localStorage},
    NeedAuthGuard,
    AuthService,
    TranslateService],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
