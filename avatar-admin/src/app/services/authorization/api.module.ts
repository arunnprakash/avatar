import { NgModule, ModuleWithProviders } from '@angular/core';
import { UserService } from './userservice.generated';
import { RoleService } from './roleservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { SellerAgentWareHouseMappingService } from './selleragentwarehousemappingservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                UserService,
                RoleService,
                QcWareHouseMappingService,
                SellerAgentWareHouseMappingService,
                TruckDriverWareHouseMappingService,
                UserDeviceService
            ]
        };
    }
}
