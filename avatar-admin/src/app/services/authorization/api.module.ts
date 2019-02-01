import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetTypeService } from './assettypeservice.generated';
import { TalukService } from './talukservice.generated';
import { RoleService } from './roleservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { AssetService } from './assetservice.generated';
import { VillageService } from './villageservice.generated';
import { GenderService } from './genderservice.generated';
import { LanguageService } from './languageservice.generated';
import { UserService } from './userservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetTypeService,
                TalukService,
                RoleService,
                StateService,
                DistrictService,
                CountryService,
                AssetService,
                VillageService,
                GenderService,
                LanguageService,
                UserService,
                UserDeviceService
            ]
        };
    }
}
