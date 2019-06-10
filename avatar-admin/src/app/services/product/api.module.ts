import { NgModule, ModuleWithProviders } from '@angular/core';
import { QualityService } from './qualityservice.generated';
import { MarketPriceService } from './marketpriceservice.generated';
import { SellerPriceHistoryService } from './sellerpricehistoryservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { AssetService } from './assetservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                QualityService,
                MarketPriceService,
                SellerPriceHistoryService,
                HolidayService,
                ProductAssetService,
                AssetTypeService,
                AssetService,
                ProductService,
                ProductRegionService
            ]
        };
    }
}
