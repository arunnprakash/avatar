import { NgModule, ModuleWithProviders } from '@angular/core';
import { UserService } from './userservice.generated';
import { LanguageService } from './languageservice.generated';
import { AssertService } from './assertservice.generated';
import { VillageService } from './villageservice.generated';
import { RoleService } from './roleservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { StateService } from './stateservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { TalukService } from './talukservice.generated';
import { GenderService } from './genderservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                UserService,
                LanguageService,
                AssertService,
                VillageService,
                RoleService,
                AssertTypeService,
                DistrictService,
                CountryService,
                StateService,
                UserDeviceService,
                TalukService,
                GenderService
            ]
        };
    }
}
