import { NgModule, ModuleWithProviders } from '@angular/core';
import { RoleService } from './roleservice.generated';
import { AssertService } from './assertservice.generated';
import { LanguageService } from './languageservice.generated';
import { CountryService } from './countryservice.generated';
import { AssertTypeService } from './asserttypeservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { TalukService } from './talukservice.generated';
import { UserService } from './userservice.generated';
import { VillageService } from './villageservice.generated';
import { GenderService } from './genderservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                RoleService,
                AssertService,
                LanguageService,
                CountryService,
                AssertTypeService,
                UserDeviceService,
                StateService,
                DistrictService,
                TalukService,
                UserService,
                VillageService,
                GenderService
            ]
        };
    }
}
