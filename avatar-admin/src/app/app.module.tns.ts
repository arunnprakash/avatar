import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptModule } from 'nativescript-angular/nativescript.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptFormsModule } from 'nativescript-angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NativeScriptHttpClientModule } from 'nativescript-angular/http-client';
/*UI Modules*/
import { NativeScriptUISideDrawerModule } from "nativescript-ui-sidedrawer/angular";
import { TNSCheckBoxModule } from 'nativescript-checkbox/angular';

import * as localStorage from 'nativescript-localstorage';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardModule } from './components/dashboard/dashboard.module';
import { UsersModule } from './components/authorization/users/users.module';
import { RolesModule } from './components/authorization/roles/roles.module';
import { ApiUrls } from './api-settings/api-urls';
import { AuthService } from "./services/auth.service";
import { TokenInterceptor } from "./services/token.interceptor";
import { NeedAuthGuard } from "./services/needauthguard";
import { APIModule } from "./services/authorization/api.module";

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
    AppRoutingModule,
    DashboardModule,
    UsersModule,
    RolesModule,
    APIModule.forRoot({context: ApiUrls.authorizationServiceApiBaseUrl}),
    NativeScriptHttpClientModule,
    NativeScriptUISideDrawerModule,
    TNSCheckBoxModule
  ],
  providers: [    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
        provide: Storage,
        useValue: localStorage
      },
    NeedAuthGuard,
    AuthService],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
