import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { fromBase64, fromResource }  from "tns-core-modules/image-source";
import { Router, ActivatedRoute } from "@angular/router";
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";

import { AuthService } from "../../services/auth.service";
import { UserDeviceService } from "../../services/authorization/userdeviceservice.generated";
import { UserDTO } from "../../services/authorization/userdto.model";
import { UserDeviceDTO } from "../../services/authorization/userdevicedto.model";

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
    constructor(private authService: AuthService, private userDeviceService: UserDeviceService, 
            private router: Router, private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
        console.info( "Home Component" );
        this.sideDrawerTransition = new SlideInOnTopTransition();
        this.userDTO = this.authService.getUserInfo();
        this.updateDeviceInfo();
    }
    pageLoaded( args ) {
        var page = args.object;
        page.actionBarHidden = true;
        this.drawer = page.getViewById( "sideDrawer" );
    }
    toggleDrawer() {
        this.drawer.toggleDrawerState();
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
    updateDeviceInfo() {
        let userDevice: UserDeviceDTO = new UserDeviceDTO();
        userDevice.user = this.userDTO;
        userDevice.language = device.language;
        userDevice.loggedIn = true;
        userDevice.manufacturer = device.manufacturer;
        userDevice.modelName = device.model;
        userDevice.modelType = device.deviceType;
        userDevice.os = device.os;
        userDevice.osVersion = device.osVersion;
        userDevice.screenScale = screen.mainScreen.scale.toString();
        userDevice.screenHeight =  screen.mainScreen.heightPixels.toString();
        userDevice.screenWidth = screen.mainScreen.widthPixels.toString();
        userDevice.uuid =  device.uuid;
        userDevice.sdkVersion = device.sdkVersion;
        this.userDeviceService.save(userDevice).subscribe(
                (userDevice: UserDeviceDTO) => {
                    console.log('User Device Information saved');
        },
        ( error ) => {
            console.log('Error while getting Gender List');
        });
    }
}
