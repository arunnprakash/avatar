import { NgModule, ModuleWithProviders } from '@angular/core';
import { OutgoingSmsService } from './outgoingsmsservice.generated';
import { IncomingSmsService } from './incomingsmsservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class OfflineAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: OfflineAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                OutgoingSmsService,
                IncomingSmsService
            ]
        };
    }
}
