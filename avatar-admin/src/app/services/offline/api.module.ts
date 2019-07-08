import { NgModule, ModuleWithProviders } from '@angular/core';
import { IncomingSmsService } from './incomingsmsservice.generated';
import { OutgoingSmsService } from './outgoingsmsservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class OfflineAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: OfflineAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                IncomingSmsService,
                OutgoingSmsService
            ]
        };
    }
}
