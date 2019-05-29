import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class ApiUrls {
	
	private static readonly avatarServiceApiBaseUrl = environment.avatarServiceApiBaseUrl;

	public static readonly masterServiceApiBaseUrl = ApiUrls.avatarServiceApiBaseUrl + environment.masterServiceApiBaseUrl;
	public static readonly authorizationServiceApiBaseUrl = ApiUrls.avatarServiceApiBaseUrl + environment.authorizationServiceApiBaseUrl;
	public static readonly productServiceApiBaseUrl = ApiUrls.avatarServiceApiBaseUrl + environment.productServiceApiBaseUrl;
	public static readonly transactionServiceApiBaseUrl = ApiUrls.avatarServiceApiBaseUrl + environment.transactionServiceApiBaseUrl;
	
}
