export const baseTemplate: string = `
<FlexboxLayout flexDirection="column" alignItems="flex-start">
    <GridLayout height="12%" row="0" rows="auto, auto, *" columns="auto, auto, *" width="100%">
        <Button text="create" col="0" row="0" width="30%" class="btn btn-primary btn-rounded-sm all-sides-margin button" (tap)="create()"></Button>
        <Button text="delete" col="1" row="0" width="30%" class="btn btn-primary btn-rounded-sm all-sides-margin button" (tap)="onDelete()"></Button>
        <CheckBox text="SelectALL" col="2" row="0" width="30%" class="all-sides-margin zero-padding" checkPadding="0px" 
        [checked]="isSelectAllChecked" (checkedChange)="selectAll($event.value)"></CheckBox>
    </GridLayout>

     <ListView height="76%" id="record-list" [items]="recordList">
        <ng-template let-rowData="item" let-rowIndex="index"> 
        <StackLayout class="all-sides-margin all-sides-padding record-content rounded-corner-border">
            <GridLayout columns="auto, *" width="100%" horizontalAlignment="stretch">
                <CheckBox col="0" row="0" colSpan="2" *ngIf="recordIdList[rowIndex]" class="text-align-right" text="Delete" [checked]="recordIdList[rowIndex].selected" (tap)="checkIfAllSelected()"></CheckBox>
            </GridLayout>
            <GridLayout *ngFor="let col of localCols" [ngSwitch]="col.dataType" columns="auto, *" (tap)="rowDataClicked(rowData)">
                <Label col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'INPUT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATE'" text="{{rowData[col.field] | date:'dd-MM-yyyy'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATETIME'" text="{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'MULTISELECT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
            </GridLayout>
        </StackLayout>
        </ng-template>
    </ListView>

    <GridLayout height="12%" horizontalAlignment="center" columns="auto, *" rows="auto,*">
        <Button text="Prev" col="0" row="0" width="30%" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button text="Next" col="1" row="0" width="30%" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
    </GridLayout>
</FlexboxLayout>
`;
