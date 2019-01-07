import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

export const componentDeclarations: any[] = [
    DashboardComponent
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
