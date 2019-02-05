import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "../../services/auth.service";
@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    private sellerAndBuyersChartData: any;
    private buyers: any[];
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
      console.info( "init DashboardComponent" );
      this.translate.get("userTitle")
      .subscribe((userTitle: any) => {
           this.userTitle = userTitle;
       });
      this.sellerAndBuyersChartData = {
              labels: [],
              datasets: []
           };
      this.monthNames.forEach( (monthName: any ) => {
           this.translate.get(monthName.i18nMonthName)
           .subscribe((monthNameValue: any) => {
               monthName.monthNameValue = monthNameValue;
           });
       });
      if (this.hasRole(['ADMIN', 'SELLER_AGENT','BUYER_AGENT'])) {
           if (this.hasRole(['ADMIN', 'SELLER_AGENT'])) {
               this.initSellerChartData();
           }
           if (this.hasRole(['ADMIN', 'BUYER_AGENT'])) {
               this.initBuyerChartData();
           }
      }

  }
  initSellerChartData() {
      let noOfSellers: any[] = [12, 30, 40, 60, 75, 90];
      let monthIndex: number = this.getMonthIndex(noOfSellers);
      var sellers = {
          label: 'Sellers',
          backgroundColor: '#33cc33',
          borderColor: '#1E88E5',
          data: noOfSellers
      };
      this.sellerAndBuyersChartData.datasets.push(sellers);
      var monthNamesForData = [];
      for (var i = 0; i < noOfSellers.length; i++) {
          var monthName = this.monthNames[monthIndex].monthNameValue;
          monthNamesForData.push(monthName);
          monthIndex = monthIndex + 1;
          if (monthIndex == 12) {
              monthIndex = 0;
          }
      }
      this.sellerAndBuyersChartData.labels = monthNamesForData;
  }
  initBuyerChartData() {
      let noOfBuyers: any[] = [15, 35, 45, 65, 80, 95];
      let monthIndex: number = this.getMonthIndex(noOfBuyers);
      var buyers = {
          label: 'Buyers',
          backgroundColor: '#ffff00',
          borderColor: '#7CB342',
          data: noOfBuyers
      };
      this.sellerAndBuyersChartData.datasets.push(buyers);
      var monthNamesForData = [];
      for (var i = 0; i < noOfBuyers.length; i++) {
          var monthName = this.monthNames[monthIndex].monthNameValue;
          monthNamesForData.push(monthName);
          monthIndex = monthIndex + 1;
          if (monthIndex == 12) {
              monthIndex = 0;
          }
      }
      this.sellerAndBuyersChartData.labels = monthNamesForData;
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
