import { OnInit } from '@angular/core';
import { AbstractBaseDetailComponent } from "./abstract.base.detail.component";
import * as _ from "lodash";

export abstract class BaseDetailComponent extends AbstractBaseDetailComponent implements OnInit {
    protected loading: boolean;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    constructor(service: any, private params?: any, private dialog?: any) {
        super(service);
        this.model = params.data.model;
        this.cols = params.data.cols;
        this.localCols = params.data.localCols;
        this.title = params.data.title;
        this.displayEditDetail = params.data.displayEditDetail;
    }

    ngOnInit() {
        super.ngOnInit();
        this.loading = false;
        this.displayAlertDialog = false;
        console.log("ngOnInit Base Detail Component.ts");
    }
    protected showLoading(value: boolean) {
        this.loading = value;
    }
    protected showAlertDialog(title: string, message: string) {
        this.displayAlertDialog = true;
        this.alertDialogTitle = title;
        this.alertDialogMessage = message;
    }
    protected closeDetailDialog() {
        this.dialog.close();
    }
}
