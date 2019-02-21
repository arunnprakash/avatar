import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { SellerComponent } from './seller/seller.component';
import { SellProductComponent } from './seller/sell-product/sell-product.component';

export const componentDeclarations: any[] = [
    DashboardComponent,
    SellerComponent,
    SellProductComponent
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
