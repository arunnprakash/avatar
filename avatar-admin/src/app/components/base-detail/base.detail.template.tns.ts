export const baseDetailTemplate: string = `
<FlexboxLayout flexDirection="column" alignItems="stretch">
    <FlexboxLayout height="12%" verticalAlignment="bottom" flexDirection="row" alignItems="stretch" justifyContent="center">
        <Label text="{{title}} Detail" verticalAlignment="center" class="text-bold text-align-center vertical-align-center"></Label>
    </FlexboxLayout>
     <GridLayout *ngIf="!displayEditDetail" height="76%" class="all-sides-margin all-sides-padding record-content rounded-corner-border">
         <ScrollView>
            <StackLayout>
                <StackLayout *ngFor="let col of cols;let i = index" [ngSwitch]="col.dataType" >
                    <GridLayout rows="auto, auto, *" columns="auto, auto, *" width="100%">
                    <Label col="0" width="120" [text]="col.header" class="text-bold text-align-left vertical-align-center"></Label>
                    <Label col="1" *ngSwitchCase="'INPUT'" [text]="model[col.field]" class="text-align-left vertical-align-center"></Label>
                    <Label col="1" *ngSwitchCase="'DATE'" text="{{model[col.field] | date:'dd-MM-yyyy'}}" class="text-align-left vertical-align-center"></Label>
                    <Label col="1" *ngSwitchCase="'DATETIME'" text="{{model[col.field] | date:'dd-MM-yyyy HH:mm:ss'}}" class="text-align-left vertical-align-center"></Label>
                    <Label col="1" *ngSwitchCase="'MULTISELECT'" [text]="model[col.field]" class="text-align-left vertical-align-center"></Label>
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
                        <TextField *ngSwitchCase="'INPUT'" row="0" col="0" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'"></TextField>
                        <DatePicker *ngSwitchCase="'DATE'" row="0" col="0" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'" ></DatePicker>
                        <DatePicker *ngSwitchCase="'DATETIME'" row="0" col="0" class="input" [hint]="col.header" [(ngModel)]="model[col.field]" [returnKeyType]="isLast?'done':'next'" ></DatePicker>
                        <StackLayout row="1" class="hr-light"></StackLayout>
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
