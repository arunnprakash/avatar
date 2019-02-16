import { OnInit } from '@angular/core';
import { AuthService } from "../../services/auth.service";

import * as _ from "lodash";

export abstract class AbstractBaseDetailComponent implements OnInit {
    protected model: any;
    protected cols: any[];
    protected localCols: any[];
    protected title: string;
    protected displayEditDetail: boolean;
    protected saved: boolean;
    protected languageCode: string;
    /*Following Abstract Fields and Methods MUST have definition in derived component class*/
    protected abstract showLoading(value: boolean);
    protected abstract showAlertDialog(title: string, message: string);
    protected abstract closeDetailDialog();
    protected abstract isModelValid(): boolean;
    constructor(private service: any, private authService: AuthService) { }
    ngOnInit() {
        console.log("ngOnInit Abstract Base Detail Component.ts");
        this.saved = false;
        this.postInit();
    }
    dateChanged(newDate, field) {
        this.model[field]= new Date(newDate);
    }
    save() {
        if (this.isModelValid()) {
            this.beforeSave();
            if (this.model.id) {
                this.showLoading(true);
                this.service.update(this.model)
                .subscribe((model: any) => {
                    _.merge(this.model, model);
                    this.showLoading(false);
                    this.saved = true;
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
                    this.saved = true;
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
    
    filterAutoCompleteSuggestion(fieldName, selectedValues) {
        let menuItem: any = _.find(this.localCols, { 'field': fieldName });
        let options: any[] = menuItem.options;
        let filteredOptions: any[] = _.filter(options, function(option) { 
            return _.find(selectedValues, option) == undefined; 
         });
        menuItem.options = filteredOptions;
    }
    protected postInit() {
        //console.log('start postInit');
        if (this.model.id) {
            //console.log('model.id found');
            this.localCols.forEach((menuItem: any ) => {
                //console.log(menuItem.field, menuItem.postInit);
                if (menuItem.postInit) {
                    //console.log('postInit required for', menuItem.field);
                    if (this.model[menuItem.initFrom]) {
                        //console.log('init From model '+ menuItem.initFrom, this.model[menuItem.initFrom]);
                        this.model[menuItem.initFrom].forEach( (item: any ) => {
                            if (menuItem.multiple) {
                                if (this.model[menuItem.field]) {
                                    this.model[menuItem.field].push(item.assetType);
                                } else {
                                    this.model[menuItem.field] = [item.assetType];
                                }
                            } else {
                                this.model[menuItem.field] = item.assetType;
                            }
                        });
                    }
                }
            });
        }
        //console.log('end postInit');
    }
    protected beforeSave() {
        //Do nothing but default implementation
    }
}
