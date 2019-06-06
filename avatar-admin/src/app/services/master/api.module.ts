import { NgModule, ModuleWithProviders } from '@angular/core';
import { StateService } from './stateservice.generated';
import { VillageService } from './villageservice.generated';
import { DistrictService } from './districtservice.generated';
import { LanguageService } from './languageservice.generated';
import { GenderService } from './genderservice.generated';
import { TalukService } from './talukservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { MarketService } from './marketservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { AssetService } from './assetservice.generated';
import { CountryService } from './countryservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                StateService,
                VillageService,
                DistrictService,
                LanguageService,
                GenderService,
                TalukService,
                WareHouseService,
                MarketService,
                AssetTypeService,
                AssetService,
                CountryService
            ]
        };
    }
}
