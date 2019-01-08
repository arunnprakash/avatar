export const baseDetailTemplate: string = `
<FlexboxLayout flexDirection="column" alignItems="flex-start">
    <GridLayout height="12%" rows="auto, auto, *" columns="auto, auto, *" width="100%">
        <Label col="0" text="{{title}} Detail" class="text-bold text-align-center vertical-align-center"></Label>
    </GridLayout>
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
            <StackLayout>
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
    <GridLayout height="12%" width="100%" horizontalAlignment="center" columns="auto, *" rows="auto,*">
        <Button *ngIf="!displayEditDetail" text="Edit" col="0" row="0" width="30%" (tap)="displayEditDetail=true" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="displayEditDetail" text="Save" col="0" row="0" width="30%" (tap)="save();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="displayEditDetail" text="Cancel" col="1" row="0" width="30%" (tap)="closeDetailDialog();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
        <Button *ngIf="!displayEditDetail" text="Ok" col="1" row="0" width="30%" (tap)="closeDetailDialog();" class="btn btn-primary btn-rounded-sm all-sides-margin button"></Button>
    </GridLayout>
</FlexboxLayout>
`;
