import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { RoleService } from './roleservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { TalukService } from './talukservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { LanguageService } from './languageservice.generated';
import { AssertService } from './assertservice.generated';
import { VillageService } from './villageservice.generated';
import { CountryService } from './countryservice.generated';
import { UserService } from './userservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                RoleService,
                AssertTypeService,
                TalukService,
                UserDeviceService,
                LanguageService,
                AssertService,
                VillageService,
                CountryService,
                UserService,
                StateService,
                DistrictService
            ]
        };
    }
}
