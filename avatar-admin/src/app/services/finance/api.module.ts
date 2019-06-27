import { NgModule, ModuleWithProviders } from '@angular/core';
import { WalletService } from './walletservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class FinanceAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: FinanceAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                WalletService
            ]
        };
    }
}
