import { NgModule, ModuleWithProviders } from '@angular/core';
import { LanguageService } from './languageservice.generated';
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
                LanguageService,
                RoleService,
                UserService
            ]
        };
    }
}
