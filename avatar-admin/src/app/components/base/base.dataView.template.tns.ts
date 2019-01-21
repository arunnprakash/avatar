export const baseDataViewTemplate: string = `
<FlexboxLayout flexDirection="column" alignItems="stretch">
    <FlexboxLayout height="12%" verticalAlignment="bottom" flexDirection="row" alignItems="flex-end" justifyContent="flex-end">
        <Label text="&#xf055;" width="24px" height="24px" verticalAlignment="center" class="fa bg-green rounded-corner-border-12 bottom-margin-5 right-margin-10 top-padding-5 left-padding-6" (tap)="create()"></Label>
        <CheckBox width="24px" height="24px" verticalAlignment="bottom" text="" class="zero-padding bottom-margin-4 right-margin-10 right-padding-10" checkPadding="0px" 
            [checked]="isSelectAllChecked" (checkedChange)="selectAll($event.value)"></CheckBox>
        <Label text="&#xf014;" width="24px" height="24px" verticalAlignment="center" class="fa rounded-corner-border-12 bottom-margin-5 right-margin-10 top-padding-5 left-padding-6" [class.bg-grey]="!enableDeleteButton" [class.bg-red]="enableDeleteButton" [isEnabled]="enableDeleteButton" (tap)="onDelete()"></Label>
    </FlexboxLayout>

     <ListView height="{{totalNumberOfPages > 1?'76%':'88%'}}" verticalAlignment="stretch" id="record-list" [items]="recordList">
        <ng-template let-rowData="item" let-rowIndex="index"> 
        <StackLayout class="all-sides-margin all-sides-padding record-content rounded-corner-border">
            <FlexboxLayout verticalAlignment="bottom" flexDirection="row" alignItems="flex-end" justifyContent="flex-end">
                <Image class="thumbnail" src="~/assets/images/avatar.png" loadMode="async"></Image>
                <CheckBox *ngIf="recordIdList[rowIndex]" class="text-align-right" text="" [checked]="recordIdList[rowIndex].selected" (tap)="checkIfAllSelected()"></CheckBox>
            </FlexboxLayout>
            <GridLayout *ngFor="let col of localCols | slice:0:5" [ngSwitch]="col.dataType" columns="auto, *" (tap)="rowDataClicked(rowData)">
                <Label col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'INPUT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATE'" text="{{rowData[col.field] | date:'dd-MM-yyyy'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'DATETIME'" text="{{rowData[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}" class="text-align-left vertical-align-center"></Label>
                <Label col="1" *ngSwitchCase="'MULTISELECT'" [text]="rowData[col.field]" class="text-align-left vertical-align-center"></Label>
                <RadAutoCompleteTextView col="1" *ngSwitchCase="'AUTOCOMPLETE'" id="{{rowData['id']}}" [(ngModel)]="rowData[col.field]" ngDefaultControl [items]="col.options" suggestMode="Suggest" displayMode="Tokens" showCloseButton="false" (loaded)="onLoaded($event)">
                    <SuggestionView tkAutoCompleteSuggestionView suggestionViewHeight="300">
                        <ng-template tkSuggestionItemTemplate let-item="item">
                            <StackLayout orientation="vertical" padding="10">
                                <Label [text]="item.text"></Label>
                            </StackLayout>
                        </ng-template>
                    </SuggestionView>
                </RadAutoCompleteTextView>
                <!--<TagGroup col="1" *ngSwitchCase="'MULTISELECT'" ntag_editMode="true" value="{{ tags }}" ntag_borderColor="#2095F2" 
                    ntag_textColor="#2095F2" ntag_bgColor="#ffffff" ntag_checkedBorderColor="#2095F2" ntag_checkedBgColor="#2095F2" 
                    ntag_checkedTextColor="#ffffff"></TagGroup>-->
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
`;
