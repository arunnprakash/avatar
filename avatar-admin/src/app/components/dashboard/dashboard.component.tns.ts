import { Component, OnInit } from '@angular/core';
import { Page } from "tns-core-modules/ui/page";
import { TranslateService } from "@ngx-translate/core";
import { ObservableArray } from "tns-core-modules/data/observable-array";

import { AuthService } from "../../services/auth.service";
import { UserService } from "../../services/authorization/userservice.generated";

import * as _ from "lodash";

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    private sellersChartData: ObservableArray<any>;
    private sellersDailyGrowthRate: ObservableArray<any>;
    private sellersMonthlyGrowthRate: ObservableArray<any>;
    private sellersYearlyGrowthRate: ObservableArray<any>;
    private buyersChartData: ObservableArray<any>;
    private buyersDailyGrowthRate: ObservableArray<any>;
    private buyersMonthlyGrowthRate: ObservableArray<any>;
    private buyersYearlyGrowthRate: ObservableArray<any>;
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
  constructor(private authService: AuthService, private translate: TranslateService,
          private userService: UserService, private page: Page) { }

  ngOnInit() {
      console.info( "Init DashboardComponent tns" );
      this.page.actionBarHidden = true;
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
              this.sellersChartData = new ObservableArray<any>();
              this.sellersDailyGrowthRate = new ObservableArray<any>();
              this.sellersMonthlyGrowthRate = new ObservableArray<any>();
              this.sellersYearlyGrowthRate = new ObservableArray<any>();
              this.initSellerChartData();
          }
          if (this.hasRole(['ADMIN', 'BUYER_AGENT'])) {
              this.buyersChartData = new ObservableArray<any>();
              this.buyersDailyGrowthRate = new ObservableArray<any>();
              this.buyersMonthlyGrowthRate = new ObservableArray<any>();
              this.buyersYearlyGrowthRate = new ObservableArray<any>();
              this.initBuyerChartData();
          }
      }
  }
  setSellerAndBuyerDailyGrowthRate() {
      this.sellersChartData = this.sellersDailyGrowthRate;
      this.buyersChartData = this.buyersDailyGrowthRate;
  }
  setSellerAndBuyerMonthlyGrowthRate() {
      this.sellersChartData = this.sellersMonthlyGrowthRate;
      this.buyersChartData = this.buyersMonthlyGrowthRate;
  }
  setSellerAndBuyerYearlyGrowthRate() {
      this.sellersChartData = this.sellersYearlyGrowthRate;
      this.buyersChartData = this.buyersYearlyGrowthRate;
  }
  initSellerChartData() {
      this.userService.sellersDailyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.sellersDailyGrowthRate.push({label: key, labelValue: value});
          } );
          this.sellersChartData = this.sellersDailyGrowthRate;
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.sellersMonthlyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.sellersMonthlyGrowthRate.push({label: key, labelValue: value});
          } );
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.sellersYearlyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.sellersYearlyGrowthRate.push({label: key, labelValue: value});
          } );
      },
      ( error ) => {
          console.log('Error', error);
      });
  }
  initBuyerChartData() {
      this.userService.buyersDailyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.buyersDailyGrowthRate.push({label: key, labelValue: value});
          } );
          this.buyersChartData = this.buyersDailyGrowthRate;
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.buyersMonthlyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.buyersMonthlyGrowthRate.push({label: key, labelValue: value});
          } );
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.buyersYearlyGrowthRate(6).subscribe((result: any) => {
          _.forOwn(result, (value, key) => {
              this.buyersYearlyGrowthRate.push({label: key, labelValue: value});
          } );
      },
      ( error ) => {
          console.log('Error', error);
      });
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
