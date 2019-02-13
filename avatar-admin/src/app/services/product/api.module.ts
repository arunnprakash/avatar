import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetService } from './assetservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { QualityService } from './qualityservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                AssetService,
                HolidayService,
                ProductAssetService,
                ProductService,
                ProductRegionService,
                QualityService,
                AssetTypeService,
                PriceHistoryService
            ]
        };
    }
}
