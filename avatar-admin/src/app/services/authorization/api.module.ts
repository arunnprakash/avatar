import { NgModule, ModuleWithProviders } from '@angular/core';
import { LanguageService } from './languageservice.generated';
import { VillageService } from './villageservice.generated';
import { StateService } from './stateservice.generated';
import { CountryService } from './countryservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { DistrictService } from './districtservice.generated';
import { AssetService } from './assetservice.generated';
import { UserService } from './userservice.generated';
import { TalukService } from './talukservice.generated';
import { RoleService } from './roleservice.generated';
import { GenderService } from './genderservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                LanguageService,
                VillageService,
                StateService,
                CountryService,
                AssetTypeService,
                DistrictService,
                AssetService,
                UserService,
                TalukService,
                RoleService,
                GenderService,
                UserDeviceService
            ]
        };
    }
}
