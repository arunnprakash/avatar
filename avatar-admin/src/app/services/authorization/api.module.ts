import { NgModule, ModuleWithProviders } from '@angular/core';
import { TalukService } from './talukservice.generated';
import { LanguageService } from './languageservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { RoleService } from './roleservice.generated';
import { VillageService } from './villageservice.generated';
import { AssetService } from './assetservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { CountryService } from './countryservice.generated';
import { GenderService } from './genderservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                TalukService,
                LanguageService,
                UserDeviceService,
                RoleService,
                VillageService,
                AssetService,
                StateService,
                DistrictService,
                AssetTypeService,
                CountryService,
                GenderService,
                UserService
            ]
        };
    }
}
