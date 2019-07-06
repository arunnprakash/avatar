import { NgModule, ModuleWithProviders } from '@angular/core';
import { BankService } from './bankservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { VillageService } from './villageservice.generated';
import { BankNameService } from './banknameservice.generated';
import { LanguageService } from './languageservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { MarketService } from './marketservice.generated';
import { CountryService } from './countryservice.generated';
import { TalukService } from './talukservice.generated';
import { DistrictService } from './districtservice.generated';
import { AssetService } from './assetservice.generated';
import { StateService } from './stateservice.generated';
import { GenderService } from './genderservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                BankService,
                SellerMerchantCommissionService,
                WareHouseService,
                VillageService,
                BankNameService,
                LanguageService,
                SellerTransportationChargeService,
                MarketService,
                CountryService,
                TalukService,
                DistrictService,
                AssetService,
                StateService,
                GenderService,
                SellerAgentCommissionService,
                AssetTypeService
            ]
        };
    }
}
