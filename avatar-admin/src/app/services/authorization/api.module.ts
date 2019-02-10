import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { VillageService } from './villageservice.generated';
import { LanguageService } from './languageservice.generated';
import { AssetService } from './assetservice.generated';
import { TalukService } from './talukservice.generated';
import { CountryService } from './countryservice.generated';
import { UserService } from './userservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { RoleService } from './roleservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                AssetTypeService,
                VillageService,
                LanguageService,
                AssetService,
                TalukService,
                CountryService,
                UserService,
                UserDeviceService,
                StateService,
                DistrictService,
                RoleService
            ]
        };
    }
}
