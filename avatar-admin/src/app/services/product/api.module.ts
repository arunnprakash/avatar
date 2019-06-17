import { NgModule, ModuleWithProviders } from '@angular/core';
import { ProductAssetService } from './productassetservice.generated';
import { AssetService } from './assetservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { HolidayService } from './holidayservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { SellerPriceHistoryService } from './sellerpricehistoryservice.generated';
import { QualityService } from './qualityservice.generated';
import { MarketPriceService } from './marketpriceservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                ProductAssetService,
                AssetService,
                ProductService,
                ProductRegionService,
                HolidayService,
                AssetTypeService,
                SellerPriceHistoryService,
                QualityService,
                MarketPriceService
            ]
        };
    }
}
