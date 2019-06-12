import { NgModule, ModuleWithProviders } from '@angular/core';
import { MarketPriceService } from './marketpriceservice.generated';
import { AssetService } from './assetservice.generated';
import { HolidayService } from './holidayservice.generated';
import { QualityService } from './qualityservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { SellerPriceHistoryService } from './sellerpricehistoryservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                MarketPriceService,
                AssetService,
                HolidayService,
                QualityService,
                AssetTypeService,
                ProductService,
                ProductRegionService,
                SellerPriceHistoryService,
                ProductAssetService
            ]
        };
    }
}
