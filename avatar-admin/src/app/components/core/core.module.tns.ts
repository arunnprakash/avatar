import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { AutocompleteComponent } from './autocomplete/autocomplete.component';

@NgModule({
  declarations: [AutocompleteComponent],
  imports: [
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class CoreModule { }
