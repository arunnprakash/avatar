export const baseDetailDataViewTemplate: string = `
    <p-scrollPanel #detail [style]="{width: '100%', height: '400px'}">
        <div *ngIf="!displayEditDetail" class="p-grid text-align-left">
            <!--<div class="p-col-12 p-grid" style="text-align:center">
                <img *ngIf="model.photo" class="card-img-top" src="data:image/png;base64,{{model.image}}" alt="image"
                                style="width: 60%; height: 60%">
                <img *ngIf="!model.photo" class="card-img-top" src="/assets/images/avatar.png" alt="image"
                                style="width: 60%; height: 60%">
            </div>-->
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
                    <p-calendar *ngSwitchCase="'DATE'" [(ngModel)]="model[col.field]" [showIcon]="true" dateFormat="dd-MM-yyyy" appendTo="body" [inputStyle]="{'width':'65%'}"></p-calendar>
                </div>
            </div>
        </div>
        <p-footer>
            <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="displayEditDetail=true" label="Edit" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="save();" label="Save" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="displayEditDetail" pButton icon="pi pi-close" (click)="closeDetailDialog()" label="Cancel" class="ui-button-success ui-button-raised ui-button-rounded"></button>
            <button type="button" *ngIf="!displayEditDetail" pButton icon="pi pi-close" (click)="closeDetailDialog()" label="Ok" class="ui-button-success ui-button-raised ui-button-rounded"></button>
        </p-footer>
    </p-scrollPanel>
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
