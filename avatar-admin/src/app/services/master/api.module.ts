import { NgModule, ModuleWithProviders } from '@angular/core';
import { VillageService } from './villageservice.generated';
import { AssetService } from './assetservice.generated';
import { DistrictService } from './districtservice.generated';
import { TalukService } from './talukservice.generated';
import { StateService } from './stateservice.generated';
import { LanguageService } from './languageservice.generated';
import { CountryService } from './countryservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { GenderService } from './genderservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                VillageService,
                AssetService,
                DistrictService,
                TalukService,
                StateService,
                LanguageService,
                CountryService,
                WareHouseService,
                GenderService,
                AssetTypeService
            ]
        };
    }
}
