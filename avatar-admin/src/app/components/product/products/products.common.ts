import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';

export const componentDeclarations: any[] = [
    ProductsComponent, 
    ProductDetailComponent
];

export const providerDeclarations: any[] = [
];

export const routes: Routes = [
    {
        path: 'products',
        component: ProductsComponent,
        outlet: 'menuRouterOutlet'
    },
    {
        path: 'product',
        component: ProductDetailComponent,
        outlet: 'menuRouterOutlet'
    }
];
