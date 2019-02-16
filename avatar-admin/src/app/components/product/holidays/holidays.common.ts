import { Routes } from '@angular/router';
import { DatePipe, SlicePipe } from '@angular/common';

import { HolidaysComponent } from './holidays/holidays.component';
import { HolidayDetailComponent } from './holiday-detail/holiday-detail.component';

export const componentDeclarations: any[] = [HolidaysComponent, HolidayDetailComponent];

export const providerDeclarations: any[] = [
    DatePipe, SlicePipe
];

export const routes: Routes = [
                               {
                                   path: 'holidays',
                                   component: HolidaysComponent,
                                   outlet: 'menuRouterOutlet'
                               },
                               {
                                   path: 'holiday',
                                   component: HolidayDetailComponent,
                                   outlet: 'menuRouterOutlet'
                               }
                           ];
