import { NgModule, ModuleWithProviders } from '@angular/core';
import { ProductRegionService } from './productregionservice.generated';
import { ProductService } from './productservice.generated';
import { PriceHistoryService } from './pricehistoryservice.generated';
import { QualityService } from './qualityservice.generated';
import { HolidayService } from './holidayservice.generated';
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
                ProductService,
                PriceHistoryService,
                QualityService,
                HolidayService,
                ProductAssetService
            ]
        };
    }
}
