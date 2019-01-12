import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { RouterModule } from '@angular/router';

import { routes, componentDeclarations } from './dashboard.common';
import { DashboardComponent } from './dashboard.component';
import { ChartModule } from 'primeng/chart';


@NgModule({
  declarations: componentDeclarations,
  imports: [
    CommonModule,
    ChartModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule,
    DashboardComponent
  ]
})
export class DashboardModule { }
