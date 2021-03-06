export const baseDetailDataViewTemplate: string = `
<FlexboxLayout flexDirection="column" alignItems="stretch">
    <GridLayout height="12%" rows="auto, auto, *" columns="auto, auto, *" class="bg-green">
      <Label text="{{title}} Detail" col="0" width="95%" horizontalAlignment="center" verticalAlignment="center" class="text-bold text-align-center vertical-align-center"></Label>
      <Label text="&#xf410;" (tap)="closeDetailDialog();" col="1" width="5%" verticalAlignment="top" horizontalAlignment="right" class="fa"></Label>
    </GridLayout>
     <GridLayout *ngIf="!displayEditDetail" height="76%" class="all-sides-margin all-sides-padding record-content rounded-corner-border">
         <ScrollView>
            <StackLayout>
                <StackLayout *ngFor="let col of cols;let i = index" [ngSwitch]="col.dataType" >
                    <GridLayout rows="auto, auto, *" columns="auto, auto, *" width="100%">
                        <Label col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                        <Label col="1" *ngSwitchCase="'INPUT'" [text]="model[col.field]" class="text-align-left vertical-align-center"></Label>
                        <Label col="1" *ngSwitchCase="'TEXTAREA'" [text]="model[col.field]" class="text-align-left vertical-align-center"></Label>
                        <Label col="1" *ngSwitchCase="'DATE'" text="{{model[col.field] | date:'dd-MM-yyyy'}}" class="text-align-left vertical-align-center"></Label>
                        <Label col="1" *ngSwitchCase="'DATETIME'" text="{{model[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}" class="text-align-left vertical-align-center"></Label>
                        <Label col="1" *ngSwitchCase="'MULTISELECT'" [text]="model[col.field]" class="text-align-left vertical-align-center"></Label>
                        <StackLayout col="1" *ngSwitchCase="'AUTOCOMPLETE'">
                            <chips *ngIf="col.multiple" [dataItems]="model[col.field]" [field]="col.optionLabel"></chips>
                            <Label *ngIf="!col.multiple" [text]="model[col.field][col.optionLabel]"></Label>
                        </StackLayout>
                        <StackLayout col="1" *ngSwitchCase="'FILE'">
                            <chips *ngIf="col.multiple" [dataItems]="model[col.field]" field="assetType.assetTypeName"></chips>
                            <Label *ngIf="!col.multiple" [text]="model[col.field]['assetType']['assetTypeName']"></Label>
                        </StackLayout>
                    </GridLayout>
                </StackLayout>
            </StackLayout>
          </ScrollView>
     </GridLayout>
     <GridLayout *ngIf="displayEditDetail" height="76%" class="all-sides-margin all-sides-padding record-content rounded-corner-border">
         <ScrollView>
            <StackLayout class="form">
                <StackLayout *ngFor="let col of localCols;let i = index;last as isLast" [ngSwitch]="col.dataType" >
                    <GridLayout rows="auto, auto, *" columns="auto, auto, *" width="100%" class="input-field">
                        <Label row="0" col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                        <TextField *ngSwitchCase="'INPUT'" row="0" col="1" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [autocapitalizationType]="col.autocapitalizationType" [keyboardType]="col.keyboardType" [returnKeyType]="isLast?'done':'next'"></TextField>
                        <TextView *ngSwitchCase="'TEXTAREA'" row="0" col="1"  class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'"></TextView>
                        <DatePicker *ngSwitchCase="'DATE'" row="0" col="1" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'" ></DatePicker>
                        <DatePicker *ngSwitchCase="'DATETIME'" row="0" col="1" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'" ></DatePicker>
                        <RadAutoCompleteTextView *ngSwitchCase="'AUTOCOMPLETE'" row="0" col="1" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" ngDefaultControl [items]="col.options" 
                            suggestMode="Suggest" displayMode="Tokens" completionMode="Contains" showCloseButton="false" 
                            (loaded)="onAutoCompleteLoaded($event, false, model[col.field], col)" 
                            (tokenAdded)="onTokenAdded($event, col);onSelectAutoComplete(col.field);" 
                            (tokenRemoved)="onTokenRemoved($event, col)"
                            [returnKeyType]="isLast?'done':'next'">
                            <SuggestionView tkAutoCompleteSuggestionView suggestionViewHeight="300">
                                <ng-template tkSuggestionItemTemplate let-item="item">
                                    <StackLayout orientation="vertical" padding="10">
                                        <Label [text]="item.text"></Label>
                                    </StackLayout>
                                </ng-template>
                            </SuggestionView>
                        </RadAutoCompleteTextView>
                        <StackLayout *ngSwitchCase="'FILE'" row="0" col="1">
                            <StackLayout *ngFor="let assetType of col.options">
                                <GridLayout rows="auto, auto, *" columns="auto, auto, *" verticalAlignment="center">
                                    <Button col="0" [text]="assetType.assetTypeName" (tap)="fileSelectedEventHandler(col.field, assetType)" class="btn btn-primary btn-rounded-sm button"></Button>
                                    <Label *ngIf="assetExist(col.field, assetType) == true" col="1" text="&#xf560;" class="fa"></Label><!--&#xf00c;-->
                                </GridLayout>
                            </StackLayout>
                        </StackLayout>
                        <StackLayout row="1" col="1" class="hr-light"></StackLayout>
                    </GridLayout>
                </StackLayout>
            </StackLayout>
         </ScrollView>
     </GridLayout>
    <FlexboxLayout height="12%" horizontalAlignment="center" verticalAlignment="bottom" flexDirection="row" alignItems="stretch" justifyContent="center">
        <Button *ngIf="!displayEditDetail" text="Edit" (tap)="displayEditDetail=true" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="displayEditDetail" text="Save" (tap)="save();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="displayEditDetail" text="Cancel" (tap)="closeDetailDialog();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="!displayEditDetail" text="Ok" (tap)="closeDetailDialog();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
    </FlexboxLayout>
</FlexboxLayout>
`;
