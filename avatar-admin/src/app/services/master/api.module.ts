import { NgModule, ModuleWithProviders } from '@angular/core';
import { CountryService } from './countryservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { AssetService } from './assetservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { StateService } from './stateservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { VillageService } from './villageservice.generated';
import { DistrictService } from './districtservice.generated';
import { LanguageService } from './languageservice.generated';
import { TalukService } from './talukservice.generated';
import { MarketService } from './marketservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { GenderService } from './genderservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                CountryService,
                WareHouseService,
                AssetService,
                SellerMerchantCommissionService,
                StateService,
                AssetTypeService,
                VillageService,
                DistrictService,
                LanguageService,
                TalukService,
                MarketService,
                SellerAgentCommissionService,
                GenderService,
                SellerTransportationChargeService
            ]
        };
    }
}
