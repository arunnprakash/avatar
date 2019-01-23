import { OnInit, ViewContainerRef } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { TranslateService } from "@ngx-translate/core";
import { AbstractBaseComponent } from "./abstract.base.component";

import { PagingAndFilterRequest } from "../../services/authorization/pagingandfilterrequest.model";
import { FilterCriteria } from "../../services/authorization/filtercriteria.model";
import { PagingAndFilterResponse } from "../../services/authorization/pagingandfilterresponse.model";
import { AuthService } from "../../services/auth.service";
import * as _ from "lodash";

export abstract class BaseComponent extends AbstractBaseComponent implements OnInit {
    protected displayEditDetail: boolean;
    protected displayDetailDialog: boolean;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    protected loading: boolean;
    constructor(service: any, authService: AuthService, translate: TranslateService, 
            private confirmationService?: any, private dialogService?: any, private detailComponent?: any, 
            private router?: Router, private activatedRoute?: ActivatedRoute, private vcRef?: ViewContainerRef) {
        super(service, authService, translate);
    }

    ngOnInit() {
        super.ngOnInit();
        this.displayEditDetail = false;
        this.displayDetailDialog = false;
        this.displayAlertDialog = false;
        this.loading = false;
        console.log("ngOnInit base.component");
    }
    protected showLoading(value: boolean) {
        this.loading = value;
    }
    protected showDetailDialog(value: boolean) {
        /*this.displayDetailDialog = value;
        this.displayEditDetail=false;
        if(!this.model.id) {
            this.displayEditDetail = true;
        }*/
        this.dialogService.open(this.detailComponent, {
            data: {
                model: this.model, cols: this.cols, localCols: this.localCols, title: this.title, displayEditDetail: this.model.id?false:true
            },
            header: this.title+' Detail',
            width: '50%',
            height: '70%'
        });
    }
    protected showAlertDialog(title: string, message: string) {
        this.displayAlertDialog = true;
        this.alertDialogTitle = title;
        this.alertDialogMessage = message;
    }
    protected onDelete() {
        this.confirmationService.confirm({
            message: 'Are you sure that you want to Delete?',
            accept: () => {
                this.delete();
            }
        });
    }
    protected recordListLoaded(): void {
        
    }
}
