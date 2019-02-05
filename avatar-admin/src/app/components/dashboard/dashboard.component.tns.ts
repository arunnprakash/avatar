import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { ObservableArray } from "tns-core-modules/data/observable-array";

import { AuthService } from "../../services/auth.service";

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  private sellers: ObservableArray<any>;
  private buyers: ObservableArray<any>;
  private userTitle: string;
  private monthNames: any[] = [
      {"i18nMonthName":"monthNames.january", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.february", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.march", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.april", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.may", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.june", "monthNameValue": ""},
      {"i18nMonthName":"monthNames.july", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.august", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.september", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.october", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.november", "monthNameValue": ""}, 
      {"i18nMonthName":"monthNames.december", "monthNameValue": ""}
  ];
  constructor(private authService: AuthService, private translate: TranslateService) { }

  ngOnInit() {
      console.info( "Init DashboardComponent tns" );
      this.translate.get("userTitle")
         .subscribe((userTitle: any) => {
              this.userTitle = userTitle;
          });
      this.monthNames.forEach( (monthName: any ) => {
          this.translate.get(monthName.i18nMonthName)
          .subscribe((monthNameValue: any) => {
              monthName.monthNameValue = monthNameValue;
          });
      });
      if (this.hasRole(['ADMIN', 'SELLER_AGENT','BUYER_AGENT'])) {
          if (this.hasRole(['ADMIN', 'SELLER_AGENT'])) {
              this.sellers = new ObservableArray<any>();
              this.initSellerChartData();
          }
          if (this.hasRole(['ADMIN', 'BUYER_AGENT'])) {
              this.buyers = new ObservableArray<any>();
              this.initBuyerChartData();
          }
      }
  }

  initSellerChartData() {
      let noOfSellers: any[] = [10, 30, 40, 60, 75, 90];
      let monthIndex: number = this.getMonthIndex(noOfSellers);
      for (var i = 0; i < noOfSellers.length; i++) {
          var monthName = this.monthNames[monthIndex].monthNameValue;
          this.sellers.push({label:monthName, labelValue: noOfSellers[i]});
          monthIndex = monthIndex + 1;
          if (monthIndex == 12) {
              monthIndex = 0;
          }
      }
  }
  initBuyerChartData() {
      let noOfBuyers: any[] = [15, 35, 45, 65, 80, 95];
      let monthIndex: number = this.getMonthIndex(noOfBuyers);
      for (var i = 0; i < noOfBuyers.length; i++) {
          var monthName = this.monthNames[monthIndex].monthNameValue;
          this.buyers.push({label:monthName, labelValue: noOfBuyers[i]});
          monthIndex = monthIndex + 1;
          if (monthIndex == 12) {
              monthIndex = 0;
          }
      }
  }
  getMonthIndex(monthlyData: any[]) {
      let currentMonthIndex = new Date().getMonth();
      for (var i = 0; i < monthlyData.length - 1; i++) {
          currentMonthIndex = currentMonthIndex - 1;
          if (currentMonthIndex == -1) {
              currentMonthIndex = 11;
          }
      }
      return currentMonthIndex;
  }
  hasRole(roles: string[]): boolean {
      return this.authService.hasRole(roles);
  }
}
