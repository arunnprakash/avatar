import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptRouterModule } from "nativescript-angular/router";
import { routes, componentDeclarations } from './core.common';
import { AutocompleteComponent } from './autocomplete/autocomplete.component';
import { ChipsComponent } from './chips/chips.component';

@NgModule({
  declarations: [AutocompleteComponent, ChipsComponent],
  imports: [
    NativeScriptCommonModule,
    NativeScriptRouterModule.forChild(routes)
  ],
  exports: [
            NativeScriptRouterModule,
            ChipsComponent
          ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class CoreModule { }
