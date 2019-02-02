import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { fromBase64, fromResource }  from "tns-core-modules/image-source";
import { Router, ActivatedRoute } from "@angular/router";

import { AuthService } from "../../services/auth.service";
import { UserDTO } from "../../services/authorization/userdto.model";

import * as _ from "lodash";

@Component( {
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    encapsulation: ViewEncapsulation.None
} )
export class HomeComponent implements OnInit {
    public sideDrawerTransition: DrawerTransitionBase;
    private drawer: RadSideDrawer;
    userDTO: UserDTO;
    constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
        console.info( "Home Component" );
        this.sideDrawerTransition = new SlideInOnTopTransition();
        this.userDTO = this.authService.getUserInfo();
    }
    pageLoaded( args ) {
        var page = args.object;
        page.actionBarHidden = true;
        this.drawer = page.getViewById( "sideDrawer" );
        console.log( "pageLoaded drawer initialized" );
    }
    toggleDrawer() {
        this.drawer.toggleDrawerState();
        console.log( "toggleDrawerState" );
    }
    isComponentSelected( componentPath: string ) {
        return false;
    }
    onNavItemTap( componentPath: string ) {
    }
    hasPhoto(){
        let assets: any[] = this.userDTO.assets;
        let asset: any = _.find(assets, function(asset) { return asset.assetType.assetTypeName == "PHOTO"; });
        return asset? true: false;
    }
    getPhoto(){
        let assets: any[] = this.userDTO.assets;
        var imageAsBase64String = _.find(assets, function(asset) { return asset.assetType.assetTypeName == "PHOTO"; }).assetValue;
        imageAsBase64String = imageAsBase64String.replace(/^data:image\/[a-z]+;base64,/, "");
        return fromBase64(imageAsBase64String);
    }
}
