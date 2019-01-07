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
                    <input *ngSwitchCase="'INPUT'" pInputText type="text" size="{{col.size?col.size:12}}" placeholder="{{col.header}}" appendTo="body" [style]="{'width':'50%'}"  (input)="dt.filter($event.target.value, col.field, col.filterMatchMode)">
                    <p-calendar *ngSwitchCase="'DATE'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="dt.filter($event, col.field, 'in')"></p-calendar>
                    <p-multiSelect *ngSwitchCase="'MULTISELECT'" [options]="col.options" defaultLabel="ALL"  appendTo="body" [style]="{'width':'100%'}" (onChange)="dt.filter($event.value, col.field, 'in')"></p-multiSelect>
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
                    <span *ngSwitchCase="'DATE'">{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</span>
                    <span *ngSwitchCase="'MULTISELECT'">{{rowData[col.field]}}</span>
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
<p-dialog [(visible)]="displayDetailDialog" [transitionOptions]="'0ms'" [modal]="true" [responsive]="true" [width]="900" [minWidth]="450" [minY]="70" 
        [maximizable]="true" [baseZIndex]="10000">
    <p-header>
        <span class="ui-dialog-title">{{title}} Detail</span>
    </p-header>
        <div *ngIf="!displayEditDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" *ngFor="let col of cols" [ngSwitch]="col.dataType">
                <div class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8" *ngSwitchCase="'INPUT'">{{model[col.field]}}</div>
                <div class="p-col-8" *ngSwitchCase="'DATE'">{{model[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</div>
                <div class="p-col-8" *ngSwitchCase="'MULTISELECT'">{{model[col.field]}}</div>
            </div>
        </div>
        <div *ngIf="displayEditDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" *ngFor="let col of localCols" [ngSwitch]="col.dataType">
                <div class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8">
                    <input *ngSwitchCase="'INPUT'" pInputText type="text" [(ngModel)]="model[col.field]" size="12" placeholder="{{col.header}}" appendTo="body" [style]="{'width':'50%'}">
                </div>
            </div>
        </div>
    <p-footer>
        <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="displayEditDetail=true" label="Edit" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="save();" label="Save" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="displayDetailDialog=false" label="Cancel" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="displayDetailDialog=false" label="Ok" class="ui-button-success ui-button-raised ui-button-rounded"></button>
    </p-footer>
</p-dialog>
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
