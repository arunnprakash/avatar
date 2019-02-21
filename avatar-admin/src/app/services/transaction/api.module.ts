import { NgModule, ModuleWithProviders } from '@angular/core';
import { SellerTransactionService } from './sellertransactionservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class TransactionAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: TransactionAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                SellerTransactionService
            ]
        };
    }
}
