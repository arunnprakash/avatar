import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetService } from './assetservice.generated';
import { UserService } from './userservice.generated';
import { RoleService } from './roleservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { VillageService } from './villageservice.generated';
import { StateService } from './stateservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { LanguageService } from './languageservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { TalukService } from './talukservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { GenderService } from './genderservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetService,
                UserService,
                RoleService,
                DistrictService,
                CountryService,
                TruckDriverWareHouseMappingService,
                VillageService,
                StateService,
                AssetTypeService,
                LanguageService,
                UserDeviceService,
                QcWareHouseMappingService,
                TalukService,
                WareHouseService,
                GenderService
            ]
        };
    }
}
