import { NgModule, ModuleWithProviders } from '@angular/core';
import { VillageService } from './villageservice.generated';
import { RoleService } from './roleservice.generated';
import { UserService } from './userservice.generated';
import { StateService } from './stateservice.generated';
import { AssetService } from './assetservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { GenderService } from './genderservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { DistrictService } from './districtservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { TalukService } from './talukservice.generated';
import { CountryService } from './countryservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { LanguageService } from './languageservice.generated';
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
                UserService,
                StateService,
                AssetService,
                UserDeviceService,
                WareHouseService,
                GenderService,
                TruckDriverWareHouseMappingService,
                DistrictService,
                QcWareHouseMappingService,
                TalukService,
                CountryService,
                AssetTypeService,
                LanguageService
            ]
        };
    }
}
