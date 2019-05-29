import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable()
export abstract class ServiceConfig {
    context?: string;
    debug?: boolean;
}
