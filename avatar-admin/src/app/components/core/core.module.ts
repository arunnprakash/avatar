import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { routes, componentDeclarations } from './core.common';
import { AutocompleteComponent } from './autocomplete/autocomplete.component';
import { ChipsComponent } from './chips/chips.component';

@NgModule({
  declarations: componentDeclarations,
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
            RouterModule
          ]
})
export class CoreModule { }
