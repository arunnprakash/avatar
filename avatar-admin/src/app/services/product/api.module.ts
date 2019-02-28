import { NgModule, ModuleWithProviders } from '@angular/core';
import { ProductAssetService } from './productassetservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { QualityService } from './qualityservice.generated';
import { AssetService } from './assetservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                ProductAssetService,
                AssetTypeService,
                ProductService,
                ProductRegionService,
                QualityService,
                AssetService,
                PriceHistoryService,
                HolidayService
            ]
        };
    }
}
