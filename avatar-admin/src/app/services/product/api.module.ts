import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductService } from './productservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { AssetService } from './assetservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { QualityService } from './qualityservice.generated';
import { HolidayService } from './holidayservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetTypeService,
                ProductService,
                ProductAssetService,
                AssetService,
                ProductRegionService,
                QualityService,
                HolidayService,
                PriceHistoryService
            ]
        };
    }
}
