import { NgModule, ModuleWithProviders } from '@angular/core';
import { SellerPriceHistoryService } from './sellerpricehistoryservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { AssetService } from './assetservice.generated';
import { QualityService } from './qualityservice.generated';
import { MarketPriceService } from './marketpriceservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductService } from './productservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                SellerPriceHistoryService,
                ProductRegionService,
                AssetService,
                QualityService,
                MarketPriceService,
                ProductAssetService,
                AssetTypeService,
                ProductService,
                HolidayService
            ]
        };
    }
}
