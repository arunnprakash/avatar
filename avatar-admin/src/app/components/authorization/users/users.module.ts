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
import { MultiSelectModule } from 'primeng/multiselect';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageModule } from 'primeng/message';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DataViewModule } from 'primeng/dataview';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ChipsModule } from 'primeng/chips';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FileUploadModule } from 'primeng/fileupload';

import { routes, componentDeclarations, providerDeclarations } from './users.common';
import { UserDetailComponent } from './user-detail/user-detail.component';

@NgModule( {
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
        //BlockUIModule,
        ProgressSpinnerModule,
        CalendarModule,
        MultiSelectModule,
        ConfirmDialogModule,
        MessageModule,
        DataViewModule,
        ScrollPanelModule,
        ChipsModule,
        AutoCompleteModule,
        FileUploadModule,
        RouterModule.forChild( routes )
    ],
    exports: [
        RouterModule
    ],
    providers: providerDeclarations,
    schemas: [NO_ERRORS_SCHEMA]
} )
export class UsersModule { }
