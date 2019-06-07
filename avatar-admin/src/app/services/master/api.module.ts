import { NgModule, ModuleWithProviders } from '@angular/core';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { LanguageService } from './languageservice.generated';
import { DistrictService } from './districtservice.generated';
import { VillageService } from './villageservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { TalukService } from './talukservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { AssetService } from './assetservice.generated';
import { CountryService } from './countryservice.generated';
import { MarketService } from './marketservice.generated';
import { StateService } from './stateservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { GenderService } from './genderservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                SellerTransportationChargeService,
                LanguageService,
                DistrictService,
                VillageService,
                WareHouseService,
                SellerAgentCommissionService,
                TalukService,
                AssetTypeService,
                AssetService,
                CountryService,
                MarketService,
                StateService,
                SellerMerchantCommissionService,
                GenderService
            ]
        };
    }
}
