import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { StatesComponent } from './states/states.component';
import { StateDetailComponent } from './state-detail/state-detail.component';

export const componentDeclarations: any[] = [StatesComponent, StateDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'states',
                                   component: StatesComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'state',
                                   component: StateDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
