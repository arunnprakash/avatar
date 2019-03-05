import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { fromBase64, fromResource }  from "tns-core-modules/image-source";
import { Router, ActivatedRoute } from "@angular/router";
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";

import { AuthService } from "../../../services/auth.service";
import { UserDeviceService } from "../../../services/authorization/userdeviceservice.generated";
import { UserDTO } from "../../../services/authorization/userdto.model";
import { UserDeviceDTO } from "../../../services/authorization/userdevicedto.model";

import * as _ from "lodash";

@Component( {
    selector: 'qc',
    templateUrl: './qc.component.html',
    styleUrls: ['./qc.component.css']
} )
export class QcComponent implements OnInit {
    public sideDrawerTransition: DrawerTransitionBase;
    private drawer: RadSideDrawer;
    userDTO: UserDTO;
    constructor(private authService: AuthService, private userDeviceService: UserDeviceService, 
            private router: Router, private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
        console.info( "On Init Qc Component" );
    }

}
