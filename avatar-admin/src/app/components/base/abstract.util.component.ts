import { OnInit } from '@angular/core';

import * as _ from "lodash";

export abstract class AbstractUtilComponent implements OnInit {

    constructor() { }

    ngOnInit() {
        console.log("ngOnInit abstract.base.component");
    }
    hasRole(role: string): boolean {
        return true;
    }
}
