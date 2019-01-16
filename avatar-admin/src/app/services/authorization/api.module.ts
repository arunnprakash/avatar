import { NgModule, ModuleWithProviders } from '@angular/core';
import { DistrictService } from './districtservice.generated';
import { VillageService } from './villageservice.generated';
import { LanguageService } from './languageservice.generated';
import { StateService } from './stateservice.generated';
import { TalukService } from './talukservice.generated';
import { RoleService } from './roleservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { VillageService } from './villageservice.generated';
import { UserService } from './userservice.generated';
import { TalukService } from './talukservice.generated';
import { CountryService } from './countryservice.generated';
import { StateService } from './stateservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                DistrictService,
                VillageService,
                LanguageService,
                StateService,
                TalukService,
                RoleService,
                UserService,
                TalukService,
                CountryService,
                StateService
            ]
        };
    }
}
