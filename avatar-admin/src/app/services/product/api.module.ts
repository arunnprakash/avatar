import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetTypeService } from './assettypeservice.generated';
import { HolidayService } from './holidayservice.generated';
import { AssetService } from './assetservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { QualityService } from './qualityservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetTypeService,
                HolidayService,
                AssetService,
                ProductAssetService,
                PriceHistoryService,
                ProductService,
                ProductRegionService,
                QualityService
            ]
        };
    }
}
