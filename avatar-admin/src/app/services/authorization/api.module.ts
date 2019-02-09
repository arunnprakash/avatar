import { NgModule, ModuleWithProviders } from '@angular/core';
import { TalukService } from './talukservice.generated';
import { AssetService } from './assetservice.generated';
import { CountryService } from './countryservice.generated';
import { VillageService } from './villageservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { LanguageService } from './languageservice.generated';
import { RoleService } from './roleservice.generated';
import { GenderService } from './genderservice.generated';
import { StateService } from './stateservice.generated';
import { DistrictService } from './districtservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class APIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: APIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                TalukService,
                AssetService,
                CountryService,
                VillageService,
                AssetTypeService,
                LanguageService,
                RoleService,
                GenderService,
                StateService,
                DistrictService,
                UserDeviceService,
                UserService
            ]
        };
    }
}
