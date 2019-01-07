import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { PagingAndFilterResponse } from './pagingandfilterresponse.model';
import { RoleDTO } from './roledto.model';
import { LoginResponse } from './loginresponse.model';
import { ZoneId } from './zoneid.model';
import { ZonedDateTime } from './zoneddatetime.model';
import { PagingAndFilterRequest } from './pagingandfilterrequest.model';
import { UserDTO } from './userdto.model';
import { FilterCriteria } from './filtercriteria.model';
import { LoginRequest } from './loginrequest.model';
import { ZoneOffset } from './zoneoffset.model';
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
        this.log('error', error);

        return Observable.throw(error);
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
