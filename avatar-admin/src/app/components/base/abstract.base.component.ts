import { OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { PagingAndFilterRequest } from "../../services/authorization/pagingandfilterrequest.model";
import { FilterCriteria } from "../../services/authorization/filtercriteria.model";
import { PagingAndFilterResponse } from "../../services/authorization/pagingandfilterresponse.model";
import { AuthService } from "../../services/auth.service";

import * as _ from "lodash";

export abstract class AbstractBaseComponent implements OnInit {

    protected numberOfRowsPerPage = 8;
    protected cols: any[];
    protected totalRecords: number;
    protected isSelectAllChecked: boolean;
    protected recordIdList: any[];
    protected recordList: any[];
    protected model: any;
    protected enableDeleteButton: boolean;
    protected languageCode: string;
    /*Following Abstract Fields and Methods MUST have definition in derived component class*/
    protected abstract title: string;
    protected abstract localCols: any[];
    protected abstract initEmptyModel();
    protected abstract onDelete(): void;
    protected abstract showLoading(value: boolean): void;
    protected abstract showDetailDialog(value: boolean): void;
    protected abstract showAlertDialog(title: string, message: string): void;
    protected abstract recordListLoaded(): void;
    constructor(private service: any, protected authService: AuthService, protected translate: TranslateService) {
    }

    ngOnInit() {
        console.log("ngOnInit abstract.base.component");
        this.initEmptyModel();
        this.cols = [
                     { field: 'id', header: 'Id', dataType: 'INPUT',size: 8 }
                 ];
        this.localCols.forEach( (col: any ) => {
            this.cols.push(col);
        });
        this.cols.push({ field: 'createdBy', header: 'Created By', dataType: 'INPUT' });
        this.cols.push({ field: 'createdDate', header: 'Created At', dataType: 'DATETIME' });
        this.cols.push({ field: 'lastModifiedBy', header: 'Last Modified By', dataType: 'INPUT' });
        this.cols.push({ field: 'lastModifiedDate', header: 'Last Modified At', dataType: 'DATETIME' });
        this.cols.push({ field: 'deleted', header: 'Deleted', dataType: 'MULTISELECT', optionLabel:"label",  options: [
                                                                                                   { label: 'DELETED', value: 0 },
                                                                                                   { label: 'NON-DELETED', value: 1 }
                                                                                               ] });
        this.recordList = [];
        this.recordIdList = [];
        this.isSelectAllChecked = false;
        this.enableDeleteButton = false;
    }
    lazyLoadRecordList(event?: any) {
        // in a real application, make a remote request to load data using state metadata from event
        // event.first = First row offset
        // event.rows = Number of rows per page
        // event.sortField = Field name to sort with
        // event.sortOrder = Sort order as number, 1 for asc and -1 for dec
        // filters: FilterMetadata object having field as key and filter value, filter matchMode as value
        // this.loading = true;
        let filters: FilterCriteria[] = [];
        if (event && event.filters) {
            for (const key in event.filters) {
                if (key != null) {
                    let filterCriteria: FilterCriteria = new FilterCriteria();
                    const filterMetadata: any = event.filters[key];
                    console.info(key, event.filters[key]);
                    const keyValues = event.filters[key].value;
                    filterCriteria.filterByItem = key;
                    filterCriteria.filterByItemValues =  _.isArray(keyValues) ? keyValues : [keyValues];
                    filters.push(filterCriteria);
                }
            }
        }
        let pageNumber = event && event.first ? event.first / event.rows : 0;
        let pageSize = event && event.rows ? event.rows:this.numberOfRowsPerPage;
        let sortBy = event && event.sortField ? event.sortField:'id';
        let sortingOrder = event &&  event.sortOrder === 1 ? 'ASC' : 'DESC';
        let pagingAndFilterRequest: PagingAndFilterRequest = new PagingAndFilterRequest();
        pagingAndFilterRequest.filters = filters;
        pagingAndFilterRequest.pageNumber = pageNumber;
        pagingAndFilterRequest.pageSize = pageSize;
        pagingAndFilterRequest.sortBy = sortBy;
        pagingAndFilterRequest.sortingOrder = sortingOrder;
        this.loadRecordList(pagingAndFilterRequest);
    }
    filter(value: any, fieldName: string) {
        let filters: FilterCriteria[] = [];
        if (value !== "") {
            let filterCriteria: FilterCriteria = new FilterCriteria();
            filterCriteria.filterByItem = fieldName;
            filterCriteria.filterByItemValues = [value];
            filters.push(filterCriteria);
        }
        let pageNumber = 0;
        let pageSize = this.numberOfRowsPerPage;
        let sortBy = 'id';
        let sortingOrder = 'DESC';
        let pagingAndFilterRequest: PagingAndFilterRequest = new PagingAndFilterRequest();
        pagingAndFilterRequest.filters = filters;
        pagingAndFilterRequest.pageNumber = pageNumber;
        pagingAndFilterRequest.pageSize = pageSize;
        pagingAndFilterRequest.sortBy = sortBy;
        pagingAndFilterRequest.sortingOrder = sortingOrder;
        this.loadRecordList(pagingAndFilterRequest);
    }
    loadRecordList(pagingAndFilterRequest: PagingAndFilterRequest) {
        this.recordIdList = [];
        this.isSelectAllChecked = false;
        this.showLoading(true);
        this.service.getResourceByFilterAndPaging(pagingAndFilterRequest)
            .subscribe((pagingAndFilterResponse: PagingAndFilterResponse) => {
            this.totalRecords = pagingAndFilterResponse.totalRecords;
            if (pagingAndFilterResponse.results) {
                this.recordList = pagingAndFilterResponse.results;
                this.recordList.forEach( (item: any ) => {
                    this.recordIdList.push({ id: item.id, selected: false });
                });
            }
            this.showLoading(false);
            this.recordListLoaded();
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting '+this.title);
        });
    }
    
    selectAll() {
        this.isSelectAllChecked = !this.isSelectAllChecked;
        this.recordIdList.forEach( (item: any ) => {
            item.selected = this.isSelectAllChecked;
        });
        this.enableDeleteButton = _.find(this.recordIdList, {"selected": true}) ? true : false;
    }
    checkIfAllSelected() {
        this.isSelectAllChecked = this.recordIdList.every( function( item: any ) {
            return item.selected == true;
        });
        this.enableDeleteButton = _.find(this.recordIdList, {"selected": true}) ? true : false;
    }
    create() {
        this.initEmptyModel();
        this.showDetailDialog(true);
    }
    dateChanged(newDate, field) {
        this.model[field]= new Date(newDate);
    }
    delete() {
        let selectedIds = _.filter(this.recordIdList, function(id) { return id.selected; });
        let idList = _.map(selectedIds, 'id');
        let idsCsv = _.join(idList, ',');
        this.showLoading(true);
        this.service.delete(idsCsv)
            .subscribe((model: any) => {
                this.showLoading(false);
                this.showAlertDialog('Success', 'Successfully Deleted '+this.title);
            },
            ( error ) => {
                this.showLoading(false);
                this.showAlertDialog('Error', 'Error while Deleting '+this.title);
            });
    }
    resetFilter() {
        
    }
    rowDataClicked(model: any) {
        this.model = model;
        this.showDetailDialog(true);
    }
    hasRole(roles: string[]): boolean {
        return this.authService.hasRole(roles);
    }
    filterAutoCompleteSuggestion(fieldName, selectedValues) {
        let menuItem: any = _.find(this.localCols, { 'field': fieldName });
        let options: any[] = menuItem.options;
        let filteredOptions: any[] = _.filter(options, function(option) { 
            return _.find(selectedValues, option) == undefined; 
         });
        menuItem.options = filteredOptions;
    }
    initFieldsLabel(prefix: string) {
        this.cols.forEach((menuItem) => {
            this.translate
            .get("common."+menuItem.field)
            .subscribe((header: any) => {
                menuItem.header = header;
            });
        });
        this.localCols.forEach((menuItem) => {
            this.translate
            .get(prefix +"."+menuItem.field)
            .subscribe((header: any) => {
                menuItem.header = header;
            });
        });
    }
    hasPhoto(assets: any[]){
        let asset: any = _.find(assets, function(asset) { return asset.assetType.assetTypeName == "PHOTO"; });
        return asset? true: false;
    }

}
