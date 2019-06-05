import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { CheckboxModule } from 'primeng/checkbox';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MultiSelectModule } from 'primeng/multiselect';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageModule } from 'primeng/message';
import { ChipsModule } from 'primeng/chips';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { routes, componentDeclarations, providerDeclarations } from './states.common';

@NgModule({
  declarations: componentDeclarations,
  imports: [
            CommonModule,
            BrowserModule,
            FormsModule,
            ReactiveFormsModule,
            CheckboxModule,
            InputTextModule,
            TableModule,
            DropdownModule,
            ButtonModule,
            PanelModule,
            DialogModule,
            BlockUIModule,
            ProgressSpinnerModule,
            CalendarModule,
            MultiSelectModule,
            ConfirmDialogModule,
            MessageModule,
            ChipsModule,
            AutoCompleteModule,
            RouterModule.forChild( routes )
  ],
  providers: providerDeclarations,
  schemas: [NO_ERRORS_SCHEMA]
})
export class StatesModule { }
