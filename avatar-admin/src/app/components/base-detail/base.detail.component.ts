import { OnInit } from '@angular/core';
import { AbstractBaseDetailComponent } from "./abstract.base.detail.component";
import { AuthService } from "../../services/auth.service";

import * as _ from "lodash";

export abstract class BaseDetailComponent extends AbstractBaseDetailComponent implements OnInit {
    protected loading: boolean;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    constructor(service: any, authService: AuthService, private params?: any, private dialog?: any) {
        super(service, authService);
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
    fileSelectedEventHandler(event, field, assetType) {
        console.log("assetType {}", assetType);
        var file = event.files[0];
        let fileReader = new FileReader();
        fileReader.onload = (e) => {
            if (!this.model[field]) {
                this.model[field] = [];
            }
            var assets = this.model[field];
            var asset = _.find(assets, function(asset) { return asset.assetType.assetTypeName == assetType.assetTypeName; });
            if (!asset) {
                asset = {};
                this.model[field].push(asset);
            }
            asset['assetValue'] = fileReader.result;
            asset['assetType'] = assetType;
        }
        fileReader.readAsDataURL(file);
    }
}
