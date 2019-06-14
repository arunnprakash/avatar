import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { CountryService } from './countryservice.generated';
import { LanguageService } from './languageservice.generated';
import { MarketService } from './marketservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { TalukService } from './talukservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { DistrictService } from './districtservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { StateService } from './stateservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { VillageService } from './villageservice.generated';
import { AssetService } from './assetservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                CountryService,
                LanguageService,
                MarketService,
                AssetTypeService,
                TalukService,
                SellerMerchantCommissionService,
                DistrictService,
                SellerTransportationChargeService,
                StateService,
                WareHouseService,
                SellerAgentCommissionService,
                VillageService,
                AssetService
            ]
        };
    }
}
