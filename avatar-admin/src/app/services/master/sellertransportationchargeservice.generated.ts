import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PagingAndFilterResponse } from './pagingandfilterresponse.model';
import { PagingAndFilterRequest } from './pagingandfilterrequest.model';
import { SellerTransportationChargeDTO } from './sellertransportationchargedto.model';
import { FilterCriteria } from './filtercriteria.model';
import { ServiceConfig } from './serviceconfig';

/**
 *  @author __Telmila__
 * 
 * 
 */
@Injectable()
export class SellerTransportationChargeService {
    private get serviceBaseURL(): string {
        return this.serviceConfig.context + '/api/seller-transportation-charge';
    }

    constructor(private httpClient: HttpClient, private serviceConfig: ServiceConfig) { }
    /* GET */
    public getAllGet(): Observable<SellerTransportationChargeDTO[]> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerTransportationChargeDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getAllExceptDeletedGet(): Observable<SellerTransportationChargeDTO[]> {
        const url = this.serviceBaseURL + '/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerTransportationChargeDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getGet(id: number): Observable<SellerTransportationChargeDTO> {
        const url = this.serviceBaseURL + '/' + id + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerTransportationChargeDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getLatestSellerTransportationChargeGet(): Observable<SellerTransportationChargeDTO> {
        const url = this.serviceBaseURL + '/latest-seller-transportation-charge';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerTransportationChargeDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* HEAD */

    /* POST */
    public savePost(arg0: SellerTransportationChargeDTO): Observable<SellerTransportationChargeDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.post<SellerTransportationChargeDTO>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getResourceByFilterAndPagingPost(arg0: PagingAndFilterRequest): Observable<PagingAndFilterResponse> {
        const url = this.serviceBaseURL + '/with-filter-and-paging';
        const params = this.createHttpParams({});

        return this.httpClient.post<PagingAndFilterResponse>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getResourceByFilterAndPagingExceptDeletedPost(arg0: PagingAndFilterRequest): Observable<PagingAndFilterResponse> {
        const url = this.serviceBaseURL + '/with-filter-and-paging/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.post<PagingAndFilterResponse>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PUT */
    public updatePut(arg0: SellerTransportationChargeDTO): Observable<SellerTransportationChargeDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.put<SellerTransportationChargeDTO>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PATCH */

    /* DELETE */
    public deleteDelete(ids: number[]): Observable<boolean> {
        const url = this.serviceBaseURL + '/' + ids + '';
        const params = this.createHttpParams({});

        return this.httpClient.delete<boolean>(url, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* OPTIONS */


    private handleError(error: HttpErrorResponse) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        //this.log('error', error);

        return throwError(error);
    }

    private log(level: string, message: any): void {
        if (this.serviceConfig.debug) {
            console[level](message);
        }
    }

    private createHttpParams(values: { [index: string]: any }): HttpParams {
        let params: HttpParams = new HttpParams();

        Object.keys(values).forEach((key: string) => {
            const value: any = values[key];
            if (value != undefined) {
                params = params.set(key, String(value));
            }
        });

        return params;
    }
}
