export const baseTemplate: string = `
<Page hasActionBar="true" actionBarHidden="true">
<ActionBar title="" isCollapsed="true"></ActionBar>
<FlexboxLayout flexDirection="column" alignItems="stretch">
    <FlexboxLayout height="12%" verticalAlignment="bottom" flexDirection="row" alignItems="flex-end" justifyContent="flex-end" class="form" (loaded)="onSearchLayoutLoaded($event)">
        <SearchBar hint="Search" width="60%" alignSelf="flex-start" class="input input-border" (clear)="onClear($event)" (submit)="onSearch($event)" (loaded)="onSearchBarLoaded($event)"></SearchBar>
        <Label text="&#xf055;" width="24px" height="24px" verticalAlignment="center" class="fa bg-green rounded-corner-border-12 bottom-margin-5 right-margin-10 top-padding-4 left-padding-5" (tap)="create()"></Label>
        <Label text="&#xf057;" width="24px" height="24px" verticalAlignment="center" class="fa rounded-corner-border-12 bottom-margin-5 right-margin-10 top-padding-4 left-padding-5" 
            [class.bg-grey]="!enableDeleteButton" [class.bg-red]="enableDeleteButton" [isEnabled]="enableDeleteButton" (tap)="onDelete()"></Label>
        <Label text="{{isSelectAllChecked?'&#xf14a;':'&#xf24d;'}}" width="24px" height="24px" verticalAlignment="center" class="fa bg-green rounded-corner-border-12 bottom-margin-5 right-margin-10 top-padding-4 left-padding-6" 
             (tap)="selectAll()"></Label>
    </FlexboxLayout>

     <ListView height="{{totalNumberOfPages > 1?'76%':'88%'}}" id="record-list" [items]="recordList">
        <ng-template let-rowData="item" let-rowIndex="index"> 
        <StackLayout class="all-sides-margin all-sides-padding record-content rounded-corner-border">
            <FlexboxLayout verticalAlignment="bottom" flexDirection="row" alignItems="flex-end" justifyContent="flex-end">
                <CheckBox *ngIf="recordIdList[rowIndex]" class="text-align-right" text="" [checked]="recordIdList[rowIndex].selected" (tap)="checkIfAllSelected()"></CheckBox>
            </FlexboxLayout>
            <GridLayout *ngFor="let col of localCols | slice:0:10" [ngSwitch]="col.dataType" columns="auto, *" (tap)="rowDataClicked(rowData)">
                <Label col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'INPUT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'TEXTAREA'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATE'" text="{{rowData[col.field] | date:'dd-MM-yyyy'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATETIME'" text="{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'MULTISELECT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <StackLayout col="1" *ngSwitchCase="'AUTOCOMPLETE'">
                    <chips *ngIf="col.multiple" [dataItems]="rowData[col.field]" [field]="col.optionLabel"></chips>
                    <Label *ngIf="!col.multiple" [text]="rowData[col.field][col.optionLabel]"></Label>
                </StackLayout>
                <StackLayout col="1" *ngSwitchCase="'FILE'">
                    <chips *ngIf="col.multiple" [dataItems]="rowData[col.field]" field="assetType.assetTypeName"></chips>
                    <Label *ngIf="!col.multiple" [text]="rowData['assetType']['assetTypeName']"></Label>
                </StackLayout>
            </GridLayout>
        </StackLayout>
        </ng-template>
    </ListView>

    <FlexboxLayout *ngIf="totalNumberOfPages > 1" height="12%" horizontalAlignment="center" verticalAlignment="bottom" flexDirection="row" alignItems="stretch" justifyContent="center">
        <Label text="&#xf049;" class="fa all-sides-padding" [isEnabled]="currentPageNumber!=1" (tap)="gotoPage(1)"></Label>
        <Label text="&#xf04a;" class="fa all-sides-padding" [isEnabled]="currentPageNumber!=1" (tap)="gotoPage(currentPageNumber - 1)"></Label>
        <Label *ngFor="let pageNumber of pageNumbers;let i = index" [attr.text]="pageNumber" class="fa all-sides-padding" [class.bg-green-round]="pageNumber==currentPageNumber" (tap)="gotoPage(pageNumber)"></Label>
        <Label text="&#xf04e;" class="fa all-sides-padding" [isEnabled]="currentPageNumber!=totalNumberOfPages" (tap)="gotoPage(currentPageNumber + 1)"></Label>
        <Label text="&#xf050;" class="fa all-sides-padding" [isEnabled]="currentPageNumber!=totalNumberOfPages" (tap)="gotoPage(totalNumberOfPages)"></Label>
    </FlexboxLayout>
</FlexboxLayout>
</Page>
`;
