import { NgModule, ModuleWithProviders } from '@angular/core';
import { QcWareHouseMappingService } from './qcwarehousemappingservice.generated';
import { UserDeviceService } from './userdeviceservice.generated';
import { TruckDriverWareHouseMappingService } from './truckdriverwarehousemappingservice.generated';
import { RoleService } from './roleservice.generated';
import { UserService } from './userservice.generated';
import { ServiceConfig } from './serviceconfig';

@NgModule({})
export class AuthorizationAPIModule {
    static forRoot(serviceConfig: ServiceConfig = {context: ''}): ModuleWithProviders {
        return {
            ngModule: AuthorizationAPIModule,
            providers: [
                {provide: ServiceConfig, useValue: serviceConfig},
                QcWareHouseMappingService,
                UserDeviceService,
                TruckDriverWareHouseMappingService,
                RoleService,
                UserService
            ]
        };
    }
}
