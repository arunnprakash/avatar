import { Routes } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

export const componentDeclarations: any[] = [
    UsersComponent, 
    UserDetailComponent
];

export const providerDeclarations: any[] = [
];

export const routes: Routes = [
    {
        path: 'users',
        component: UsersComponent,
        outlet: 'menuRouterOutlet'
    },
    {
        path: 'user',
        component: UserDetailComponent,
        outlet: 'menuRouterOutlet'
    }
];
