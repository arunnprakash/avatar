export const baseTemplate: string = `
<p-table #dt [columns]="cols" [totalRecords]="totalRecords" [value]="recordList" [paginator]="true" [rows]="numberOfRowsPerPage" 
[resizableColumns]="false" columnResizeMode="expand" [lazy]="true" sortOrder="-1" sortField="id" (onLazyLoad)="lazyLoadRecordList($event)">
        <ng-template pTemplate="caption">
            <div class="p-grid">
                <div class="p-col-4 p-col-nogutter">
                    
                </div>
                <div class="p-col-4 p-md-4 p-col-nogutter text-align-center">
                    <h3>{{title}}s</h3>
                </div>
                <div class="p-col-4 p-md-4 p-col-nogutter text-align-right text-align-bottom">
                    <button type="button" pButton icon="pi pi-close" (click)="create()" label="Create" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                    <button type="button" pButton icon="pi pi-close" (click)="onDelete()" label="Delete" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                    <button type="button" pButton icon="pi pi-close" (click)="resetFilter()" label="Reset Filter" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                </div>
            </div>
        </ng-template>
        <ng-template pTemplate="header" let-columns>
            <tr>
                <th *ngFor="let col of columns" [pSortableColumn]="col.field">
                    {{col.header}}<p-sortIcon [field]="col.field"></p-sortIcon>
                </th>
                <th>
                    Delete Selected
                </th>
            </tr>
            <tr>
                <th *ngFor="let col of columns" [ngSwitch]="col.dataType" class="text-align-left" >
                    <input *ngSwitchCase="'INPUT'" pInputText type="text" size="{{col.size?col.size:12}}" [placeholder]="col.header" appendTo="body" [style]="{'width':'50%'}"  (input)="dt.filter($event.target.value, col.field, col.filterMatchMode)">
                    <p-calendar *ngSwitchCase="'DATE'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="dt.filter($event, col.field, 'in')"></p-calendar>
                    <p-calendar *ngSwitchCase="'DATETIME'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="dt.filter($event, col.field, 'in')"></p-calendar>
                    <p-multiSelect *ngSwitchCase="'MULTISELECT'" [options]="col.options" [optionLabel]="col.optionLabel" defaultLabel="ALL"  appendTo="body" [style]="{'width':'100%'}" (onChange)="dt.filter($event.value, col.field, 'in')"></p-multiSelect>
                    <p-autoComplete *ngSwitchCase="'AUTOCOMPLETE'" immutable="false" (completeMethod)="filterAutoCompleteSuggestion(col.field, model[col.field])" [suggestions]="col.options" (onSelect)="dt.filter($event, col.field, 'in')" forceSelection="true" [field]="col.optionLabel" [dropdown]="true" [multiple]="true" [size]="30" [placeholder]="col.header"></p-autoComplete>
                </th>
                <th class="text-align-left">
                    <p-checkbox binary="true" label="SelectAll" [(ngModel)]="isSelectAllChecked" (onChange)="selectAll()"></p-checkbox>
                </th>
            </tr>
        </ng-template>
        <ng-template pTemplate="body" let-rowData let-rowIndex="rowIndex" let-columns="columns">
            <tr class="hand-cursor tableRowHoverCss" >
                <td *ngFor="let col of columns" [ngSwitch]="col.dataType" (click)="rowDataClicked(rowData)">
                    <span *ngSwitchCase="'INPUT'">{{rowData[col.field]}}</span>
                    <span *ngSwitchCase="'DATE'">{{rowData[col.field] | date:'dd-MM-yyyy'}}</span>
                    <span *ngSwitchCase="'DATETIME'">{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</span>
                    <span *ngSwitchCase="'MULTISELECT'">{{rowData[col.field]}}</span>
                    <!--<p-multiSelect *ngSwitchCase="'MULTISELECT'" readonly="true" [options]="col.options" [(ngModel)]="rowData[col.field]" [optionLabel]="col.optionLabel" defaultLabel="ALL"  appendTo="body" [style]="{'width':'100%'}"></p-multiSelect>-->
                    <span *ngSwitchCase="'AUTOCOMPLETE'">
                        <p-chips *ngIf="rowData[col.field] && col.multiple" max="1" disabled="true" [(ngModel)]="rowData[col.field]" [field]="col.optionLabel" ngDefaultControl></p-chips>
                        <div *ngIf="rowData[col.field] && !col.multiple">{{rowData[col.field][col.optionLabel]}}</div>
                    </span>
                    <span *ngSwitchCase="'FILE'">
                        <p-chips *ngIf="col.multiple" disabled="true" [(ngModel)]="rowData[col.field]" field="assetType.assetTypeName" ngDefaultControl></p-chips>
                        <div *ngIf="!col.multiple" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field]['assetType']['assetTypeName']}}</div>
                    </span>
                </td>
                <td>
                     <p-checkbox *ngIf="recordIdList[rowIndex]" binary="true" [(ngModel)]="recordIdList[rowIndex].selected" (onChange)="checkIfAllSelected();"></p-checkbox>
                </td>
            </tr>
        </ng-template>
    <ng-template pTemplate="emptymessage" let-columns>
        <tr>
            <td [attr.colspan]="columns.length + 1">
                No records found
            </td>
        </tr>
    </ng-template>
</p-table>
<p-blockUI [blocked]="loading" [target]="dt"></p-blockUI>
<p-confirmDialog #cd header="Delete {{title}}s" icon="pi pi-exclamation-triangle">
    <p-footer>
        <button type="button" pButton icon="pi pi-times" label="No" (click)="cd.reject()"></button>
        <button type="button" pButton icon="pi pi-check" label="Yes" (click)="cd.accept()"></button>
    </p-footer>
</p-confirmDialog>
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
`;
