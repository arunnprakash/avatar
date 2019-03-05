import { NgModule, ModuleWithProviders } from '@angular/core';
import { RoleService } from './roleservice.generated';
import { CountryService } from './countryservice.generated';
import { GenderService } from './genderservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { LanguageService } from './languageservice.generated';
import { AssetService } from './assetservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { UserService } from './userservice.generated';
import { TalukService } from './talukservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { StateService } from './stateservice.generated';
import { VillageService } from './villageservice.generated';
import { DistrictService } from './districtservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                RoleService,
                CountryService,
                GenderService,
                WareHouseService,
                LanguageService,
                AssetService,
                UserDeviceService,
                UserService,
                TalukService,
                QcWareHouseMappingService,
                StateService,
                VillageService,
                DistrictService,
                TruckDriverWareHouseMappingService,
                AssetTypeService
            ]
        };
    }
}
