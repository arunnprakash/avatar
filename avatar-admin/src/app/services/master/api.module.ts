import { NgModule, ModuleWithProviders } from '@angular/core';
import { WareHouseService } from './warehouseservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { AssetService } from './assetservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { CountryService } from './countryservice.generated';
import { LanguageService } from './languageservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { StateService } from './stateservice.generated';
import { MarketService } from './marketservice.generated';
import { GenderService } from './genderservice.generated';
import { DistrictService } from './districtservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { VillageService } from './villageservice.generated';
import { TalukService } from './talukservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                WareHouseService,
                SellerTransportationChargeService,
                AssetService,
                SellerMerchantCommissionService,
                CountryService,
                LanguageService,
                SellerAgentCommissionService,
                StateService,
                MarketService,
                GenderService,
                DistrictService,
                AssetTypeService,
                VillageService,
                TalukService
            ]
        };
    }
}
