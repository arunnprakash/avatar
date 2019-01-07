import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";

@Component( {
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    encapsulation: ViewEncapsulation.None
} )
export class HomeComponent implements OnInit {
    public sideDrawerTransition: DrawerTransitionBase;
    private drawer: RadSideDrawer;
    constructor() { }

    ngOnInit() {
        console.info( "Home Component" );
        this.sideDrawerTransition = new SlideInOnTopTransition();
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
}
