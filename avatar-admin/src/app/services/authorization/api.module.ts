import { NgModule, ModuleWithProviders } from '@angular/core';
import { DistrictService } from './districtservice.generated';
import { RoleService } from './roleservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { StateService } from './stateservice.generated';
import { AssertService } from './assertservice.generated';
import { CountryService } from './countryservice.generated';
import { GenderService } from './genderservice.generated';
import { VillageService } from './villageservice.generated';
import { TalukService } from './talukservice.generated';
import { UserService } from './userservice.generated';
import { LanguageService } from './languageservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                DistrictService,
                RoleService,
                AssertTypeService,
                StateService,
                AssertService,
                CountryService,
                GenderService,
                VillageService,
                TalukService,
                UserService,
                LanguageService
            ]
        };
    }
}
