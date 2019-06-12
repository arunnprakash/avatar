import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PagingAndFilterResponse } from './pagingandfilterresponse.model';
import { AssetDTO } from './assetdto.model';
import { SellerPriceHistoryDTO } from './sellerpricehistorydto.model';
import { PagingAndFilterRequest } from './pagingandfilterrequest.model';
import { AssetTypeDTO } from './assettypedto.model';
import { BaseDTO } from './basedto.model';
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
export class SellerPriceHistoryService {
    private get serviceBaseURL(): string {
        return this.serviceConfig.context + '/api/price-history';
    }

    constructor(private httpClient: HttpClient, private serviceConfig: ServiceConfig) { }
    /* GET */
    public getAll(): Observable<SellerPriceHistoryDTO[]> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerPriceHistoryDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getAllExceptDeleted(): Observable<SellerPriceHistoryDTO[]> {
        const url = this.serviceBaseURL + '/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerPriceHistoryDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public get(id: number): Observable<SellerPriceHistoryDTO> {
        const url = this.serviceBaseURL + '/' + id + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerPriceHistoryDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getPriceForProduct(productId: number, qualityId: number, pricePublishedDate: string): Observable<SellerPriceHistoryDTO> {
        const url = this.serviceBaseURL + '/price/' + productId + '/' + qualityId + '/' + pricePublishedDate + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<SellerPriceHistoryDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* HEAD */

    /* POST */
    public save(arg0: SellerPriceHistoryDTO): Observable<SellerPriceHistoryDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.post<SellerPriceHistoryDTO>(url, arg0, {params: params})
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

    public getProductsForUser(userId: number, talukId: number, districtId: number, stateId: number, pagingAndFilterRequest: PagingAndFilterRequest): Observable<PagingAndFilterResponse> {
        const url = this.serviceBaseURL + '/' + userId + '/' + talukId + '/' + districtId + '/' + stateId + '';
        const params = this.createHttpParams({});

        return this.httpClient.post<PagingAndFilterResponse>(url, pagingAndFilterRequest, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PUT */
    public update(arg0: SellerPriceHistoryDTO): Observable<SellerPriceHistoryDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.put<SellerPriceHistoryDTO>(url, arg0, {params: params})
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
