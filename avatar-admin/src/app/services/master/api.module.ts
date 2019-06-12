import { NgModule, ModuleWithProviders } from '@angular/core';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { StateService } from './stateservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { LanguageService } from './languageservice.generated';
import { GenderService } from './genderservice.generated';
import { MarketService } from './marketservice.generated';
import { AssetService } from './assetservice.generated';
import { DistrictService } from './districtservice.generated';
import { CountryService } from './countryservice.generated';
import { TalukService } from './talukservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { VillageService } from './villageservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                SellerMerchantCommissionService,
                SellerAgentCommissionService,
                StateService,
                WareHouseService,
                SellerTransportationChargeService,
                LanguageService,
                GenderService,
                MarketService,
                AssetService,
                DistrictService,
                CountryService,
                TalukService,
                AssetTypeService,
                VillageService
            ]
        };
    }
}
