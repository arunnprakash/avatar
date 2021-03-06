export const baseDetailTemplate: string = `
    <div #detail>
        <div *ngIf="!displayEditDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" *ngFor="let col of cols" [ngSwitch]="col.dataType">
                <div  class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8" *ngSwitchCase="'INPUT'">{{model[col.field]}}</div>
                <div class="p-col-8" *ngSwitchCase="'TEXTAREA'">{{model[col.field]}}</div>
                <div class="p-col-8" *ngSwitchCase="'DATE'">{{model[col.field] | date:'dd-MM-yyyy'}}</div>
                <div class="p-col-8" *ngSwitchCase="'DATETIME'">{{model[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</div>
                <div class="p-col-8" *ngSwitchCase="'MULTISELECT'">{{model[col.field]}}</div>
                <!--<p-multiSelect *ngSwitchCase="'MULTISELECT'" readonly="true" [options]="col.options" [(ngModel)]="model[col.field]" [optionLabel]="col.optionLabel"  appendTo="body" [style]="{'width':'100%'}"></p-multiSelect>-->
                <span *ngSwitchCase="'AUTOCOMPLETE'">
                    <p-chips *ngIf="col.multiple" disabled="true" [(ngModel)]="model[col.field]" [field]="col.optionLabel" ngDefaultControl></p-chips>
                    <div *ngIf="!col.multiple" class="p-col-8">{{model[col.field][col.optionLabel]}}</div>
                </span>
                <span *ngSwitchCase="'FILE'">
                    <p-chips *ngIf="col.multiple" disabled="true" [(ngModel)]="model[col.field]" field="assetType.assetTypeName" ngDefaultControl></p-chips>
                    <div *ngIf="!col.multiple" class="p-col-8">{{model[col.field]['assetType']['assetTypeName']}}</div>
                </span>
            </div>
        </div>
        <div *ngIf="displayEditDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" *ngFor="let col of localCols" [ngSwitch]="col.dataType">
                <div class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8">
                    <input *ngSwitchCase="'INPUT'" pInputText type="text" [(ngModel)]="model[col.field]" size="23" [placeholder]="col.header" appendTo="body" [style]="{'width':'50%'}">
                    <textarea *ngSwitchCase="'TEXTAREA'" [(ngModel)]="model[col.field]" [rows]="5" [cols]="30" pInputTextarea autoResize="autoResize"></textarea>
                    <p-calendar *ngSwitchCase="'DATE'" [ngModel]="model[col.field] | date:'yyyy-MM-dd'" (ngModelChange)="dateChanged($event, col.field)" [showIcon]="true" dateFormat="dd-MM-yyyy" appendTo="body" [inputStyle]="{'width':'65%'}"></p-calendar>
                    <p-calendar *ngSwitchCase="'DATETIME'" [ngModel]="model[col.field] | date:'yyyy-MM-dd'" (ngModelChange)="dateChanged($event, col.field)" [showIcon]="true" dateFormat="dd-MM-yyyy" appendTo="body" [inputStyle]="{'width':'65%'}"></p-calendar>
                    <p-multiSelect *ngSwitchCase="'MULTISELECT'" [options]="col.options" [(ngModel)]="model[col.field]" appendTo="body" [style]="{'width':'100%'}"></p-multiSelect>
                    <p-autoComplete *ngSwitchCase="'AUTOCOMPLETE'" immutable="false" (onSelect)="onSelectAutoComplete(col.field)" (completeMethod)="filterAutoCompleteSuggestion(col.field, model[col.field])" [(ngModel)]="model[col.field]" [suggestions]="col.options" forceSelection="true" [field]="col.optionLabel" [dropdown]="true" [multiple]="col.multiple" [size]="30" [placeholder]="col.header"></p-autoComplete>
                </div>
            </div>
        </div>
        <p-footer>
            <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="displayEditDetail=true" label="Edit" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="save();" label="Save" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="closeDetailDialog()" label="Cancel" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="closeDetailDialog()" label="Ok" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        </p-footer>
    <div>
    <p-dialog [(visible)]="displayAlertDialog" 
        [style]="{'width': '300px !important', 'height': '200px !important','min-width': '300px !important', 'min-height': '200px !important'}" 
        [minY]="70" [maximizable]="false">
        <p-header>
            <p-message *ngIf="alertDialogTitle == 'Error'" severity="error" text="Error" [closable]="false"></p-message>
            <p-message *ngIf="alertDialogTitle == 'Success'" severity="success" text="Success" [closable]="false"></p-message>
        </p-header>
            {{alertDialogMessage}}
        <p-footer>
            <button type="button" pButton icon="pi pi-close" (click)="displayAlertDialog=false" label="Ok" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        </p-footer>
    </p-dialog>
        <!--<p-blockUI [blocked]="loading" [target]="detail"></p-blockUI>-->
`;
