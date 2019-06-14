import { NgModule, ModuleWithProviders } from '@angular/core';
import { RoleService } from './roleservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { UserService } from './userservice.generated';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                RoleService,
                TruckDriverWareHouseMappingService,
                UserDeviceService,
                UserService,
                QcWareHouseMappingService
            ]
        };
    }
}
