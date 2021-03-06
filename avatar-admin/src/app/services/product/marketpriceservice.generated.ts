import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PagingAndFilterResponse } from './pagingandfilterresponse.model';
import { AssetDTO } from './assetdto.model';
import { PagingAndFilterRequest } from './pagingandfilterrequest.model';
import { AssetTypeDTO } from './assettypedto.model';
import { FilterCriteria } from './filtercriteria.model';
import { MarketPriceDTO } from './marketpricedto.model';
import { ProductDTO } from './productdto.model';
import { QualityDTO } from './qualitydto.model';
import { ServiceConfig } from './serviceconfig';

/**
 *  @author __Telmila__
 * 
 * 
 */
@Injectable()
export class MarketPriceService {
    private get serviceBaseURL(): string {
        return this.serviceConfig.context + '/api/market-price';
    }

    constructor(private httpClient: HttpClient, private serviceConfig: ServiceConfig) { }
    /* GET */
    public getAll(): Observable<MarketPriceDTO[]> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<MarketPriceDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getAllExceptDeleted(): Observable<MarketPriceDTO[]> {
        const url = this.serviceBaseURL + '/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.get<MarketPriceDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public get(id: number): Observable<MarketPriceDTO> {
        const url = this.serviceBaseURL + '/' + id + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<MarketPriceDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* HEAD */

    /* POST */
    public save(arg0: MarketPriceDTO): Observable<MarketPriceDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.post<MarketPriceDTO>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getResourceByFilterAndPaging(arg0: PagingAndFilterRequest): Observable<PagingAndFilterResponse> {
        const url = this.serviceBaseURL + '/with-filter-and-paging';
        const params = this.createHttpParams({});

        return this.httpClient.post<PagingAndFilterResponse>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getResourceByFilterAndPagingExceptDeleted(arg0: PagingAndFilterRequest): Observable<PagingAndFilterResponse> {
        const url = this.serviceBaseURL + '/with-filter-and-paging/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.post<PagingAndFilterResponse>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PUT */
    public update(arg0: MarketPriceDTO): Observable<MarketPriceDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.put<MarketPriceDTO>(url, arg0, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PATCH */

    /* DELETE */
    public delete(ids: number[]): Observable<boolean> {
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
