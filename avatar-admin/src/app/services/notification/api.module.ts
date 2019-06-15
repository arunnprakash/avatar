import { NgModule, ModuleWithProviders } from '@angular/core';
import { NotificationService } from './notificationservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class NotificationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: NotificationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                NotificationService
            ]
        };
    }
}
