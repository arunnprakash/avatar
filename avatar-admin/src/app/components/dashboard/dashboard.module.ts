import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { RouterModule } from '@angular/router';

import { routes, componentDeclarations } from './dashboard.common';
import { DashboardComponent } from './dashboard.component';

import { PanelModule } from 'primeng/panel';
import { ChartModule } from 'primeng/chart';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: componentDeclarations,
  imports: [
    CommonModule,
    ButtonModule,
    PanelModule,
    ChartModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule,
    DashboardComponent
  ]
})
export class DashboardModule { }
