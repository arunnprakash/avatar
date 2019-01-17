import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssertTypeService } from './asserttypeservice.generated';
import { AssertService } from './assertservice.generated';
import { RoleService } from './roleservice.generated';
import { UserService } from './userservice.generated';
import { StateService } from './stateservice.generated';
import { TalukService } from './talukservice.generated';
import { LanguageService } from './languageservice.generated';
import { VillageService } from './villageservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssertTypeService,
                AssertService,
                RoleService,
                UserService,
                StateService,
                TalukService,
                LanguageService,
                VillageService,
                DistrictService,
                CountryService
            ]
        };
    }
}
