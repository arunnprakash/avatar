import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { Router, ActivatedRoute } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { UserDTO } from "../../services/authorization/userdto.model";

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
        console.log(this.userDTO);
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
    logout() {
        this.authService.clearAll();
        this.router.navigate(['/login'], { relativeTo: this.activatedRoute }).then( (e) => {
            if (e) {
                console.log("Navigation to login successful!");
              } else {
                console.log("Navigation to login has failed!");
              }
        });
    }
}
