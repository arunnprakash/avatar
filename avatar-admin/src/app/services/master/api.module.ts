import { NgModule, ModuleWithProviders } from '@angular/core';
import { WareHouseService } from './warehouseservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { CountryService } from './countryservice.generated';
import { TalukService } from './talukservice.generated';
import { StateService } from './stateservice.generated';
import { AssetService } from './assetservice.generated';
import { DistrictService } from './districtservice.generated';
import { LanguageService } from './languageservice.generated';
import { VillageService } from './villageservice.generated';
import { GenderService } from './genderservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                WareHouseService,
                AssetTypeService,
                CountryService,
                TalukService,
                StateService,
                AssetService,
                DistrictService,
                LanguageService,
                VillageService,
                GenderService
            ]
        };
    }
}
