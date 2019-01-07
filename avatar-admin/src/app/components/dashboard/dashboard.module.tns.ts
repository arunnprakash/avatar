import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptRouterModule } from "nativescript-angular/router";

import { routes, componentDeclarations } from './dashboard.common';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  declarations: componentDeclarations,
  imports: [
    NativeScriptCommonModule,
    NativeScriptRouterModule.forChild(routes)
  ],
  exports: [
            NativeScriptRouterModule,
            DashboardComponent
          ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class DashboardModule { }
