import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { SellerComponent } from './seller/seller.component';

export const componentDeclarations: any[] = [
    DashboardComponent,
    SellerComponent
];

export const providerDeclarations: any[] = [
];

export const routes: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent,
        outlet: 'menuRouterOutlet'
    }
];
