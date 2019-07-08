import { NgModule, ModuleWithProviders } from '@angular/core';
import { GenderService } from './genderservice.generated';
import { TalukService } from './talukservice.generated';
import { StateService } from './stateservice.generated';
import { CountryService } from './countryservice.generated';
import { BankNameService } from './banknameservice.generated';
import { VillageService } from './villageservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { MarketService } from './marketservice.generated';
import { BankService } from './bankservice.generated';
import { AssetService } from './assetservice.generated';
import { DistrictService } from './districtservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { LanguageService } from './languageservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                GenderService,
                TalukService,
                StateService,
                CountryService,
                BankNameService,
                VillageService,
                SellerMerchantCommissionService,
                MarketService,
                BankService,
                AssetService,
                DistrictService,
                SellerAgentCommissionService,
                SellerTransportationChargeService,
                WareHouseService,
                AssetTypeService,
                LanguageService
            ]
        };
    }
}
