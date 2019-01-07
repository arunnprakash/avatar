import { NgModule, ModuleWithProviders } from '@angular/core';
import { RoleService } from './roleservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                RoleService,
                UserService
            ]
        };
    }
}
