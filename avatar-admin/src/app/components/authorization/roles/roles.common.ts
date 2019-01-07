import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { RolesComponent } from './roles/roles.component';
import { RoleDetailComponent } from './role-detail/role-detail.component';

export const componentDeclarations: any[] = [RolesComponent, RoleDetailComponent];

export const providerDeclarations: any[] = [
DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'roles',
                                   component: RolesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'role',
                                   component: RoleDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
