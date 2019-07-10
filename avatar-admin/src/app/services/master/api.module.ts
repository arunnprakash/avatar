import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { VillageService } from './villageservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { BankService } from './bankservice.generated';
import { BankNameService } from './banknameservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { LanguageService } from './languageservice.generated';
import { TalukService } from './talukservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { DistrictService } from './districtservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { MarketService } from './marketservice.generated';
import { CountryService } from './countryservice.generated';
import { AssetService } from './assetservice.generated';
import { StateService } from './stateservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                VillageService,
                SellerTransportationChargeService,
                BankService,
                BankNameService,
                SellerMerchantCommissionService,
                AssetTypeService,
                LanguageService,
                TalukService,
                WareHouseService,
                DistrictService,
                SellerAgentCommissionService,
                MarketService,
                CountryService,
                AssetService,
                StateService
            ]
        };
    }
}
