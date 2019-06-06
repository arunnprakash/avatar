import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetService } from './assetservice.generated';
import { ProductService } from './productservice.generated';
import { HolidayService } from './holidayservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { MarketPriceService } from './marketpriceservice.generated';
import { QualityService } from './qualityservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { SellerPriceHistoryService } from './sellerpricehistoryservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetService,
                ProductService,
                HolidayService,
                AssetTypeService,
                ProductRegionService,
                MarketPriceService,
                QualityService,
                ProductAssetService,
                SellerPriceHistoryService
            ]
        };
    }
}
