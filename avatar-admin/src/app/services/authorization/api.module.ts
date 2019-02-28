import { NgModule, ModuleWithProviders } from '@angular/core';
import { VillageService } from './villageservice.generated';
import { RoleService } from './roleservice.generated';
import { LanguageService } from './languageservice.generated';
import { GenderService } from './genderservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { TalukService } from './talukservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { AssetService } from './assetservice.generated';
import { StateService } from './stateservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                VillageService,
                RoleService,
                LanguageService,
                GenderService,
                WareHouseService,
                AssetTypeService,
                DistrictService,
                CountryService,
                UserDeviceService,
                TalukService,
                TruckDriverWareHouseMappingService,
                AssetService,
                StateService,
                UserService
            ]
        };
    }
}
