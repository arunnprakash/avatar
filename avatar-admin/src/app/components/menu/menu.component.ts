import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MenuItem } from 'primeng/api';
import { AuthService } from "../../services/auth.service";

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, AfterViewInit {
  menuItems: any[];
  selectedMenuItem: any;
  @Input('menuDrawer')
  drawer: any;
constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.menuItems = [
          {label: 'Dashboard', visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['dashboard'] } }] },
          {label: 'Users',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['users']     } }] },
          {label: 'Roles',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['roles']     } }] }
      ];
  }
  ngAfterViewInit() {
      this.naviagateByMenuIem(this.menuItems[1]);
  }
  naviagateByMenuIem(menuItem: any) {
      this.selectedMenuItem = menuItem;
      this.router.navigate(menuItem.routerLink, { relativeTo: this.activatedRoute }).then( (e) => {
          if (e) {
              console.log("Navigation to "+menuItem.label+" successful!");
            } else {
              console.log("Navigation to "+menuItem.label+" has failed!");
            }
      });
      if (this.drawer) {
          this.drawer.toggleDrawerState();
      }
  }
  isMenuSelected(menuItem: any) {
      return this.selectedMenuItem && this.selectedMenuItem.label == menuItem.label;
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
