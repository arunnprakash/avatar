export const baseDataViewTemplate: string = `
<p-dataView #dataView [value]="recordList" [paginator]="true" [rows]="numberOfRowsPerPage" [totalRecords]="totalRecords" 
    [lazy]="true" (onLazyLoad)="lazyLoadRecordList($event)" paginatorPosition="both" filterBy="id"
    [sortField]="sortField" [sortOrder]="sortOrder">
        <p-header>
            <div class="ui-helper-clearfix">
                <div class="ui-g">
                    <div class="ui-g-12 ui-md-12">
                        <div class="ui-g">
                            <div class="ui-g-4 ui-md-4 text-align-left text-align-bottom">
                                <p-dataViewLayoutOptions></p-dataViewLayoutOptions>
                            </div>
                            <div class="ui-g-4 ui-md-4 text-align-center text-align-bottom">
                                <h3>{{title}}s</h3>
                            </div>
                            <div class="ui-col-4 ui-md-4 ui-col-nogutter text-align-right text-align-bottom">
                                <button type="button" pButton icon="pi pi-close" (click)="create()" label="Create" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                                <button type="button" pButton icon="pi pi-close" (click)="delete()" label="Delete" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                                <button type="button" pButton icon="pi pi-close" (click)="resetFilter()" label="Reset Filter" class="ui-button-success ui-button-raised ui-button-rounded"></button>
                            </div>
                        </div>
                    </div>
                    <div class="ui-col-12 ui-md-12 ui-col-nogutter text-align-left text-align-bottom">
                        <span *ngFor="let col of cols" [ngSwitch]="col.dataType">
                            <input *ngSwitchCase="'INPUT'" pInputText type="text" size="{{col.size?col.size:12}}" [placeholder]="col.header" appendTo="body" [style]="{'width':'50%'}"  (input)="filter($event.target.value, col.field, col.filterMatchMode)">
                            <p-calendar *ngSwitchCase="'DATE'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="filter($event, col.field, 'in')"></p-calendar>
                            <p-calendar *ngSwitchCase="'DATETIME'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="filter($event, col.field, 'in')"></p-calendar>
                            <p-multiSelect *ngSwitchCase="'MULTISELECT'" [options]="col.options" [optionLabel]="col.optionLabel" defaultLabel="ALL"  appendTo="body" [style]="{'width':'10%'}" (onChange)="filter($event.value, col.field, 'in')"></p-multiSelect>
                            <p-autoComplete *ngSwitchCase="'AUTOCOMPLETE'" immutable="false" (completeMethod)="filterAutoCompleteSuggestion(col.field, model[col.field])" (onSelect)="filter($event, col.field, 'in')" [suggestions]="col.options" forceSelection="true" [field]="col.optionLabel" [dropdown]="true" [multiple]="true" [size]="30" [placeholder]="col.header"></p-autoComplete>
                        </span>
                        <span>
                            <p-checkbox binary="true" label="SelectAll" [(ngModel)]="isSelectAllChecked" (onChange)="selectAll()"></p-checkbox>
                        </span>
                    </div>
                </div>
            </div>
        </p-header>
        <ng-template let-rowData let-rowIndex="rowIndex" let-columns="columns" pTemplate="listItem">
            <div class="ui-g hand-cursor tableRowHoverCss" style="padding: 2em;border-bottom: 1px solid #d9d9d9">
                <div class="ui-g-12 ui-md-3" style="text-align:center" (click)="displayDetailDialog(rowData)">
                        <img *ngIf="hasPhoto(rowData.assets)" class="card-img-top" [src]="getPhoto(rowData.assets)" alt="image"
                            style="width: 100%">
                        <img *ngIf="!hasPhoto(rowData.assets)" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                            style="width: 100%">
                </div>
                <div class="ui-g-12 ui-md-8" (click)="rowDataClicked(rowData)">
                     <div class="ui-g" *ngFor="let col of localCols" [ngSwitch]="col.dataType">
                        <div class="ui-g-4 ui-sm-6 text-bold text-align-left">{{col.header}}</div>
                        <div *ngSwitchCase="'INPUT'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field]}}</div>
                        <div *ngSwitchCase="'DATE'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field] | date:'dd-MM-yyyy'}}</div>
                        <div *ngSwitchCase="'DATETIME'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</div>
                        <div *ngSwitchCase="'MULTISELECT'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field]}}</div>
                        <!--<p-multiSelect *ngSwitchCase="'MULTISELECT'" readonly="true" defaultLabel="ALL" [options]="col.options" [(ngModel)]="rowData[col.field]" [optionLabel]="col.optionLabel" appendTo="body" [style]="{'width':'100%'}"></p-multiSelect>-->
                        <span *ngSwitchCase="'AUTOCOMPLETE'">
                            <p-chips *ngIf="col.multiple" disabled="true" [(ngModel)]="rowData[col.field]" [field]="col.optionLabel" ngDefaultControl></p-chips>
                            <div *ngIf="!col.multiple" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field][col.optionLabel]}}</div>
                        </span>
                    </div>
                </div>
                <div class="ui-g-12 ui-md-1">
                    <p-checkbox *ngIf="recordIdList[rowIndex]" binary="true" [(ngModel)]="recordIdList[rowIndex].selected" (onChange)="checkIfAllSelected();"></p-checkbox>
                </div>
            </div>
        </ng-template>
        <ng-template let-rowData let-rowIndex="rowIndex" let-columns="columns" pTemplate="gridItem">
            <div style="padding:.5em" class="ui-g-12 ui-md-3 hand-cursor tableRowHoverCss" (click)="displayDetailDialog(rowData)">
                <p-panel [header]="rowData[localCols[0].field]" [style]="{'text-align':'center'}">
                        <img *ngIf="hasPhoto(rowData.assets)" class="card-img-top" [src]="getPhoto(rowData.assets)" alt="image"
                            style="width: 100%" width="60">
                        <img *ngIf="!hasPhoto(rowData.assets)" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                            style="width: 100%" width="60">
                    <div>{{rowData[localCols[0].field]}} - {{rowData[localCols[1].field]}}</div>
                    <hr class="ui-widget-content" style="border-top:0">
                    <p-checkbox *ngIf="recordIdList[rowIndex]" binary="true" [(ngModel)]="recordIdList[rowIndex].selected" (onChange)="checkIfAllSelected();"></p-checkbox>
                </p-panel>
            </div>
        </ng-template>
</p-dataView>
<p-blockUI [blocked]="loading" [target]="dataView"></p-blockUI>
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
