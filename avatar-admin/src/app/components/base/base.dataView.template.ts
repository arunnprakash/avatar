export const baseDataViewTemplate: string = `<p-dataView #dataView [value]="recordList" [paginator]="true" [rows]="numberOfRowsPerPage" [totalRecords]="totalRecords" 
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
                            <input *ngSwitchCase="'INPUT'" pInputText type="text" size="{{col.size?col.size:12}}" placeholder="{{col.header}}" appendTo="body" [style]="{'width':'50%'}"  (input)="filter($event.target.value, col.field, col.filterMatchMode)">
                            <p-calendar *ngSwitchCase="'DATE'" appendTo="body" [inputStyle]="{'width':'65%'}" [showIcon]="true" dateFormat="dd-mm-yy" (onSelect)="filter($event, col.field, 'in')"></p-calendar>
                            <p-multiSelect *ngSwitchCase="'MULTISELECT'" [options]="col.options" defaultLabel="ALL"  appendTo="body" [style]="{'width':'10%'}" (onChange)="filter($event.value, col.field, 'in')"></p-multiSelect>
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
                        <img *ngIf="rowData.photo" class="card-img-top" src="data:image/png;base64,{{rowData.image}}" alt="image"
                            style="width: 100%">
                        <img *ngIf="!rowData.photo" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                            style="width: 100%">
                </div>
                <div class="ui-g-12 ui-md-8" (click)="displayDetailDialog(rowData)">
                     <div class="ui-g" *ngFor="let col of cols" [ngSwitch]="col.dataType">
                        <div class="ui-g-4 ui-sm-6 text-bold text-align-left">{{col.header}}</div>
                        <div *ngSwitchCase="'INPUT'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field]}}</div>
                        <div *ngSwitchCase="'DATE'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}</div>
                        <div *ngSwitchCase="'MULTISELECT'" class="ui-g-8 ui-sm-6 text-align-left">{{rowData[col.field]}}</div>
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
                        <img *ngIf="rowData.photo" class="card-img-top" src="data:image/png;base64,{{rowData.image}}" alt="image"
                            style="width: 100%" width="60">
                        <img *ngIf="!rowData.photo" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                            style="width: 100%" width="60">
                    <div class="car-detail">{{rowData[localCols[0].field]}} - {{rowData[localCols[1].field]}}</div>
                    <hr class="ui-widget-content" style="border-top:0">
                    <p-checkbox *ngIf="recordIdList[rowIndex]" binary="true" [(ngModel)]="recordIdList[rowIndex].selected" (onChange)="checkIfAllSelected();"></p-checkbox>
                </p-panel>
            </div>
        </ng-template>
</p-dataView>
<!--    <p-blockUI [target]="dataView" [blocked]="loading">
        <p-progressSpinner></p-progressSpinner>
    </p-blockUI> -->
    <p-dialog [(visible)]="showDetailDialog" [transitionOptions]="'0ms'" [modal]="true" [responsive]="true" [width]="900" [minWidth]="450" [minY]="70" 
        [maximizable]="true" [baseZIndex]="10000">
        <p-header>
            <span class="ui-dialog-title">{{title}} Detail</span>
        </p-header>
        <div *ngIf="!editDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" style="text-align:center">
                <img *ngIf="model.photo" class="card-img-top" src="data:image/png;base64,{{model.image}}" alt="image"
                                style="width: 100%" width="60">
                <img *ngIf="!model.photo" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                                style="width: 100%" width="60">
            </div>
            <div class="p-col-12 p-grid" *ngFor="let col of cols">
                <div class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8">{{model[col.field]}}</div>
            </div>
        </div>
        <div *ngIf="editDetail" class="p-grid text-align-left">
            <div class="p-col-12 p-grid" *ngFor="let col of localCols" [ngSwitch]="col.dataType">
                <div class="p-col-4 text-bold">{{col.header}}</div>
                <div class="p-col-8">
                    <input *ngSwitchCase="'INPUT'" pInputText type="text" [(ngModel)]="model[col.field]" size="12" placeholder="{{col.header}}" appendTo="body" [style]="{'width':'50%'}">
                </div>
            </div>
        </div>
        <p-footer>
            <button type="button" *ngIf="!editDetail" pButton icon="pi pi-close" (click)="editDetail=true" label="Edit" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="editDetail" pButton icon="pi pi-close" (click)="save();" label="Save" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="editDetail" pButton icon="pi pi-close" (click)="showDetailDialog=false" label="Cancel" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="!editDetail" pButton icon="pi pi-close" (click)="showDetailDialog=false" label="Ok" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        </p-footer>
    </p-dialog>
`;
