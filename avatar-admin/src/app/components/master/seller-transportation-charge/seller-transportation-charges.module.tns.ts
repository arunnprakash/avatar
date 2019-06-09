import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptModule } from 'nativescript-angular/nativescript.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { NativeScriptFormsModule } from 'nativescript-angular/forms';
import { NativeScriptUIAutoCompleteTextViewModule } from "nativescript-ui-autocomplete/angular";
import { NativeScriptRouterModule } from "nativescript-angular/router";
import { routes, componentDeclarations, providerDeclarations } from './seller-transportation-charges.common';

@NgModule({
    declarations: [componentDeclarations],
    imports: [
      NativeScriptModule,
      NativeScriptCommonModule,
      NativeScriptFormsModule,
      NativeScriptUIAutoCompleteTextViewModule,
      NativeScriptRouterModule.forChild(routes)
    ],
    exports: [
      NativeScriptRouterModule
     ],
    providers: providerDeclarations,
    schemas: [NO_ERRORS_SCHEMA]
})
export class SellerTransportationChargesModule { }
