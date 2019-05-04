import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { StateService } from './stateservice.generated';
import { CountryService } from './countryservice.generated';
import { LanguageService } from './languageservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { AssetService } from './assetservice.generated';
import { TalukService } from './talukservice.generated';
import { UserService } from './userservice.generated';
import { RoleService } from './roleservice.generated';
import { VillageService } from './villageservice.generated';
import { DistrictService } from './districtservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                QcWareHouseMappingService,
                StateService,
                CountryService,
                LanguageService,
                WareHouseService,
                AssetService,
                TalukService,
                UserService,
                RoleService,
                VillageService,
                DistrictService,
                TruckDriverWareHouseMappingService,
                UserDeviceService,
                AssetTypeService
            ]
        };
    }
}
