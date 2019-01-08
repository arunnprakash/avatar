import { OnInit } from '@angular/core';

import * as _ from "lodash";

export abstract class AbstractBaseDetailComponent implements OnInit {
    protected model: any;
    protected cols: any[];
    protected localCols: any[];
    protected title: string;
    protected displayEditDetail: boolean;

    /*Following Abstract Fields and Methods MUST have definition in derived component class*/
    protected abstract showLoading(value: boolean);
    protected abstract showAlertDialog(title: string, message: string);
    protected abstract closeDetailDialog();
    protected abstract isModelValid(): boolean;
    constructor(private service: any) {
    }
    ngOnInit() {
        console.log("ngOnInit Abstract Base Detail Component.ts");
    }
    dateChanged(newDate, field) {
        this.model[field]= new Date(newDate);
    }
    save() {
        if (this.isModelValid()) {
            if (this.model.id) {
                this.showLoading(true);
                this.service.update(this.model)
                .subscribe((model: any) => {
                    _.merge(this.model, model);
                    this.showLoading(false);
                    this.closeDetailDialog();
                    this.showAlertDialog('Success', 'Successfully Updated '+this.title);
                },
                ( error ) => {
                    this.showLoading(false);
                    this.showAlertDialog('Error', 'Error while Updating '+this.title);
                });
            } else {
                this.showLoading(true);
                this.service.save(this.model)
                .subscribe((model: any) => {
                    this.model = model;
                    this.showLoading(false);
                    this.closeDetailDialog();
                    this.showAlertDialog('Success', 'Successfully Saved '+this.title);
                },
                ( error ) => {
                    this.showLoading(false);
                    this.showAlertDialog('Error', 'Error while Saving '+this.title);
                });
            }
        } else {
            
        }
    }
}
