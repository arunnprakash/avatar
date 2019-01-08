import { NgModule, ModuleWithProviders } from '@angular/core';
import { UserService } from './userservice.generated';
import { RoleService } from './roleservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                UserService,
                RoleService
            ]
        };
    }
}
