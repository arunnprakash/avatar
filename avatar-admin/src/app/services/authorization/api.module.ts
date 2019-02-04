import { NgModule, ModuleWithProviders } from '@angular/core';
import { LanguageService } from './languageservice.generated';
import { DistrictService } from './districtservice.generated';
import { VillageService } from './villageservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { AssetService } from './assetservice.generated';
import { GenderService } from './genderservice.generated';
import { TalukService } from './talukservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { CountryService } from './countryservice.generated';
import { StateService } from './stateservice.generated';
import { RoleService } from './roleservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                LanguageService,
                DistrictService,
                VillageService,
                UserDeviceService,
                AssetService,
                GenderService,
                TalukService,
                AssetTypeService,
                CountryService,
                StateService,
                RoleService,
                UserService
            ]
        };
    }
}
