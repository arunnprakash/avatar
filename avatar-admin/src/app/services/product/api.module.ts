import { NgModule, ModuleWithProviders } from '@angular/core';
import { HolidayService } from './holidayservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { ProductService } from './productservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { QualityService } from './qualityservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { AssetService } from './assetservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                HolidayService,
                ProductAssetService,
                ProductService,
                PriceHistoryService,
                AssetTypeService,
                QualityService,
                ProductRegionService,
                AssetService
            ]
        };
    }
}
