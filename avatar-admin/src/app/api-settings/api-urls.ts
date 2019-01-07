import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class ApiUrls {
    private static readonly avatarServiceApiBaseUrl = environment.avatarServiceApiBaseUrl;
    public static readonly authorizationServiceApiBaseUrl = ApiUrls.avatarServiceApiBaseUrl + environment.authorizationServiceApiBaseUrl;
}
