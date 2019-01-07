import { Routes } from '@angular/router';

import { NeedAuthGuard } from './services/needauthguard';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from "./components/home/home.component";
import { UsersComponent } from "./components/authorization/users/users/users.component";
import { UserDetailComponent } from "./components/authorization/users/user-detail/user-detail.component";
import { RolesComponent } from "./components/authorization/roles/roles/roles.component";
import { RoleDetailComponent } from "./components/authorization/roles/role-detail/role-detail.component";

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full',
    },
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [ NeedAuthGuard ],
        children: [
                   {
                       path: 'users',
                       component: UsersComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'user',
                       component: UserDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'roles',
                       component: RolesComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'role',
                       component: RoleDetailComponent,
                       outlet: 'menuRouterOutlet'
                   },
                   {
                       path: 'dashboard',
                       component: DashboardComponent,
                       outlet: 'menuRouterOutlet'
                   }
       ]
    }
];
