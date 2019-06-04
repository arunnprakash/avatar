import { NgModule, ModuleWithProviders } from '@angular/core';
import { ProductService } from './productservice.generated';
import { ProductRegionService } from './productregionservice.generated';
import { ProductAssetService } from './productassetservice.generated';
import { QualityService } from './qualityservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { AssetTypeService } from './assettypeservice.generated';
import { AssetService } from './assetservice.generated';
import { HolidayService } from './holidayservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class ProductAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: ProductAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                ProductService,
                ProductRegionService,
                ProductAssetService,
                QualityService,
                PriceHistoryService,
                AssetTypeService,
                AssetService,
                HolidayService
            ]
        };
    }
}
