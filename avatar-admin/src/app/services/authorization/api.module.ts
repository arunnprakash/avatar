import { NgModule, ModuleWithProviders } from '@angular/core';
import { VillageService } from './villageservice.generated';
import { CountryService } from './countryservice.generated';
import { GenderService } from './genderservice.generated';
import { LanguageService } from './languageservice.generated';
import { DistrictService } from './districtservice.generated';
import { AssertService } from './assertservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { UserService } from './userservice.generated';
import { RoleService } from './roleservice.generated';
import { TalukService } from './talukservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { StateService } from './stateservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                VillageService,
                CountryService,
                GenderService,
                LanguageService,
                DistrictService,
                AssertService,
                AssertTypeService,
                UserService,
                RoleService,
                TalukService,
                UserDeviceService,
                StateService
            ]
        };
    }
}
