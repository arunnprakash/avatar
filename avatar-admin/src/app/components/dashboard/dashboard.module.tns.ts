import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptRouterModule } from "nativescript-angular/router";

import { NativeScriptUIChartModule } from "nativescript-ui-chart/angular";

import { routes, componentDeclarations } from './dashboard.common';
import { DashboardComponent } from './dashboard.component';
import { SellProductComponent } from './seller/sell-product/sell-product.component';
import { SellerOrderComponent } from './selleragent/seller-order/seller-order.component';

@NgModule({
  declarations: componentDeclarations,
  imports: [
    NativeScriptCommonModule,
    NativeScriptUIChartModule,
    NativeScriptRouterModule.forChild(routes)
  ],
  exports: [
    NativeScriptRouterModule,
    DashboardComponent
  ],
  entryComponents: [
    SellProductComponent,
    SellerOrderComponent
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
