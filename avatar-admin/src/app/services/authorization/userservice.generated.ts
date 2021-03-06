import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PagingAndFilterResponse } from './pagingandfilterresponse.model';
import { RoleDTO } from './roledto.model';
import { LoginResponse } from './loginresponse.model';
import { PagingAndFilterRequest } from './pagingandfilterrequest.model';
import { UserDTO } from './userdto.model';
import { FilterCriteria } from './filtercriteria.model';
import { LoginRequest } from './loginrequest.model';
import { ServiceConfig } from './serviceconfig';

/**
 *  @author __ArunPrakash__
 * 
 * 
 */
@Injectable()
export class UserService {
    private get serviceBaseURL(): string {
        return this.serviceConfig.context + '/api/user';
    }

    constructor(private httpClient: HttpClient, private serviceConfig: ServiceConfig) { }
    /* GET */
    public getAll(): Observable<UserDTO[]> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getAllExceptDeleted(): Observable<UserDTO[]> {
        const url = this.serviceBaseURL + '/with-includes-only-non-deleted';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public get(id: number): Observable<UserDTO> {
        const url = this.serviceBaseURL + '/' + id + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public logout(): Observable<boolean> {
        const url = this.serviceBaseURL + '/logout';
        const params = this.createHttpParams({});

        return this.httpClient.get<boolean>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public sellersDailyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/sellers/daily/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public sellersMonthlyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/sellers/monthly/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public sellersYearlyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/sellers/yearly/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public buyersDailyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/buyers/daily/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public buyersMonthlyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/buyers/monthly/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public buyersYearlyGrowthRate(depth: number): Observable<{ [index: string]: number }> {
        const url = this.serviceBaseURL + '/buyers/yearly/growth-rate/' + depth + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: number }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getSellerAgentForSeller(sellerId: number): Observable<UserDTO> {
        const url = this.serviceBaseURL + '/seller/' + sellerId + '/seller-agent';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getBuyerAgentForBuyer(buyerId: number): Observable<UserDTO> {
        const url = this.serviceBaseURL + '/buyer/' + buyerId + '/buyer-agent';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getTruckDriverForSellerAgent(sellerAgentId: number): Observable<UserDTO> {
        const url = this.serviceBaseURL + '/seller-agent/' + sellerAgentId + '/truck-driver';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getSellerAgentByWareHouse(wareHouseId: number): Observable<UserDTO[]> {
        const url = this.serviceBaseURL + '/seller-agent/warehouse/' + wareHouseId + '';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getSellersBySellerAgent(sellerAgentId: number): Observable<UserDTO[]> {
        const url = this.serviceBaseURL + '/seller-agent/' + sellerAgentId + '/seller';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO[]>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getSellerAgentForTruckDriver(truckDriverId: number): Observable<UserDTO> {
        const url = this.serviceBaseURL + '/truck-driver/' + truckDriverId + '/seller-agent';
        const params = this.createHttpParams({});

        return this.httpClient.get<UserDTO>(url, {params: params})
            .pipe(catchError(this.handleError));
    }

    public getWareHouseForTruckDriver(truckDriverId: number): Observable<{ [index: string]: any }> {
        const url = this.serviceBaseURL + '/truck-driver/' + truckDriverId + '/warehouse';
        const params = this.createHttpParams({});

        return this.httpClient.get<{ [index: string]: any }>(url, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* HEAD */

    /* POST */
    public save(arg0: UserDTO): Observable<UserDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.post<UserDTO>(url, arg0, {params: params})
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

    public login(loginRequest: LoginRequest): Observable<LoginResponse> {
        const url = this.serviceBaseURL + '/login';
        const params = this.createHttpParams({});

        return this.httpClient.post<LoginResponse>(url, loginRequest, {params: params})
            .pipe(catchError(this.handleError));
    }


    /* PUT */
    public update(arg0: UserDTO): Observable<UserDTO> {
        const url = this.serviceBaseURL + '';
        const params = this.createHttpParams({});

        return this.httpClient.put<UserDTO>(url, arg0, {params: params})
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
