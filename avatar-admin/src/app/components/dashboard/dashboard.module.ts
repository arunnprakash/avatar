import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RouterModule } from '@angular/router';

import { routes, componentDeclarations } from './dashboard.common';
import { DashboardComponent } from './dashboard.component';
import { SellProductComponent } from './seller/sell-product/sell-product.component';
import { SellerOrderComponent } from './selleragent/seller-order/seller-order.component';
import { QcOrderComponent } from './qc/qc-order/qc-order.component';

import { PanelModule } from 'primeng/panel';
import { ChartModule } from 'primeng/chart';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { DialogModule } from 'primeng/dialog';
import { MessageModule } from 'primeng/message';
import { BlockUIModule } from 'primeng/blockui';
import { InputTextModule } from 'primeng/inputtext';
import { AutoCompleteModule } from 'primeng/autocomplete';

@NgModule({
  declarations: componentDeclarations,
  imports: [
    CommonModule,
    BrowserModule, 
    FormsModule,
    ButtonModule,
    PanelModule,
    ChartModule,
    DataViewModule,
    ScrollPanelModule,
    AutoCompleteModule,
    InputTextModule,
    BlockUIModule,
    MessageModule,
    DialogModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule,
    DashboardComponent
  ],
  entryComponents: [
    SellProductComponent,
    SellerOrderComponent,
    QcOrderComponent
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
