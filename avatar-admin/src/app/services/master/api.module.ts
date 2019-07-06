import { NgModule, ModuleWithProviders } from '@angular/core';
import { BankNameService } from './banknameservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { VillageService } from './villageservice.generated';
import { WareHouseService } from './warehouseservice.generated';
import { CountryService } from './countryservice.generated';
import { AssetService } from './assetservice.generated';
import { SellerMerchantCommissionService } from './sellermerchantcommissionservice.generated';
import { GenderService } from './genderservice.generated';
import { SellerTransportationChargeService } from './sellertransportationchargeservice.generated';
import { MarketService } from './marketservice.generated';
import { TalukService } from './talukservice.generated';
import { BankService } from './bankservice.generated';
import { LanguageService } from './languageservice.generated';
import { SellerAgentCommissionService } from './selleragentcommissionservice.generated';
import { DistrictService } from './districtservice.generated';
import { StateService } from './stateservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class MasterAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: MasterAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                BankNameService,
                AssetTypeService,
                VillageService,
                WareHouseService,
                CountryService,
                AssetService,
                SellerMerchantCommissionService,
                GenderService,
                SellerTransportationChargeService,
                MarketService,
                TalukService,
                BankService,
                LanguageService,
                SellerAgentCommissionService,
                DistrictService,
                StateService
            ]
        };
    }
}
