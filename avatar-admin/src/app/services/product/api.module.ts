import { NgModule, ModuleWithProviders } from '@angular/core';
import { ProductRegionService } from './productregionservice.generated';
import { AssetService } from './assetservice.generated';
import { QualityService } from './qualityservice.generated';
import { ProductService } from './productservice.generated';
import { MarketPriceService } from './marketpriceservice.generated';
import { HolidayService } from './holidayservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
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
                ProductRegionService,
                AssetService,
                QualityService,
                ProductService,
                MarketPriceService,
                HolidayService,
                AssetTypeService,
                SellerPriceHistoryService,
                ProductAssetService
            ]
        };
    }
}
