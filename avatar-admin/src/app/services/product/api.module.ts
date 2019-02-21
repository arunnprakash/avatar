import { NgModule, ModuleWithProviders } from '@angular/core';
import { AssetService } from './assetservice.generated';
import { QualityService } from './qualityservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductAssetService } from './productassetservice.generated';
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
                QualityService,
                HolidayService,
                ProductService,
                ProductRegionService,
                AssetTypeService,
                ProductAssetService,
                PriceHistoryService
            ]
        };
    }
}
