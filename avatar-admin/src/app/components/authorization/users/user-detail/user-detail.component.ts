import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailDataViewTemplate } from '../../../base-detail/base.detail.dataView.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';

import { UserService } from "../../../../services/authorization/userservice.generated";

@Component({
  selector: 'user-detail',
  template: baseDetailDataViewTemplate,
  styles: [baseDetailCss]
})
export class UserDetailComponent extends BaseDetailComponent implements OnInit {

    constructor(userService: UserService, params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
        super(userService, params, dialog);
    }

    ngOnInit() {
        super.ngOnInit();
        console.log("ngOnInit User Detail Component.ts");
    }
    protected isModelValid(): boolean {
        return true;
    }
    filterAutoComplete(field) {
        console.log("filterAutoComplete");
        let existingOptions: any = [];
        this.localCols.forEach( (menuItem: any ) => {
            if (menuItem.field == field) {
                existingOptions = menuItem.options;
                menuItem.options = [];
            }
        });
        this.localCols.forEach( (menuItem: any ) => {
            if (menuItem.field == field) {
                existingOptions.forEach( (option: any ) => {
                    menuItem.options.push(option);
                });
                console.log("menu optiona :: " + menuItem.options);
            }
        });
        
    }
}
