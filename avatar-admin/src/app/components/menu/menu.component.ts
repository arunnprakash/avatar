import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { MenuItem } from 'primeng/api';
import { AuthService } from "../../services/auth.service";

import * as _ from "lodash";

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
          {label: 'Dashboard', visible: this.authService.hasRole(['ADMIN', 'SELLER_AGENT','SELLER_TRUCK_DRIVER', 'BUYER_AGENT', 'SELLER','BUYER', 'WAREHOUSE_QC']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['dashboard'] } }] },
          {label: 'Users',     visible: this.authService.hasRole(['ADMIN', 'SELLER_AGENT','BUYER_AGENT']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['users']     } }] },
          {label: 'WareHouses',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['warehouses']     } }] },
          {label: 'Markets',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['markets']     } }] },
          {label: 'Qc-WareHouse-Mappings',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['qc-warehouse-mappings']     } }] },
          {label: 'SellerAgent-WareHouse-Mappings',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['selleragent-warehouse-mappings']     } }] },
          {label: 'TruckDriver-WareHouse-Mappings',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['truckdriver-warehouse-mappings']     } }] },
          {label: 'Roles',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['roles']     } }] },
          {label: 'Users AssetTypes',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['usersAssetTypes']     } }] },
          {label: 'Languages',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['languages']     } }] },
          {label: 'Villages',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['villages']     } }] },
          {label: 'Taluks',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['taluks']     } }] },
          {label: 'Districts',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['districts']     } }] },
          {label: 'States',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['states']     } }] },
          {label: 'Products',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['products']     } }] },
          {label: 'Products AssetTypes',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['productsAssetTypes']     } }] },
          {label: 'Product Regions',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['productregions']     } }] },
          {label: 'Seller Agent Commissions',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['seller-agent-commissions']     } }] },
          {label: 'Seller Transportation Charge',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['seller-transportation-charges']     } }] },
          {label: 'Seller Merchant Commissions',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['seller-merchant-commissions']     } }] },
          {label: 'Seller Prices',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['prices']     } }] },
          {label: 'Market Prices',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['market-prices']     } }] },
          {label: 'Holidays',     visible: this.authService.hasRole(['ADMIN']),   icon: 'pi pi-fw pi-comments', routerLink: [{ outlets: { menuRouterOutlet: ['holidays']     } }] }
      ];
  }
  ngAfterViewInit() {
      let menuItem: any  = _.find(this.menuItems, { 'visible': true });
      if (menuItem) {
          this.naviagateByMenuIem(menuItem);
      }
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
