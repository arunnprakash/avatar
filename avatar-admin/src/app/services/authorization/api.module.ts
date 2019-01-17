import { NgModule, ModuleWithProviders } from '@angular/core';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { UserService } from './userservice.generated';
import { CountryService } from './countryservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { AssertService } from './assertservice.generated';
import { VillageService } from './villageservice.generated';
import { TalukService } from './talukservice.generated';
import { LanguageService } from './languageservice.generated';
import { RoleService } from './roleservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                StateService,
                DistrictService,
                UserService,
                CountryService,
                AssertTypeService,
                AssertService,
                VillageService,
                TalukService,
                LanguageService,
                RoleService
            ]
        };
    }
}
