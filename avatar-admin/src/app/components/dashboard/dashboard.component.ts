import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "../../services/auth.service";
import { UserService } from "../../services/authorization/userservice.generated";
@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {
    private sellerAndBuyersChartData: any;
    private sellerAndBuyersDailyGrowthRate: any;
    private sellerAndBuyersMonthlyGrowthRate: any;
    private sellerAndBuyersYearlyGrowthRate: any;
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
  constructor(private authService: AuthService, private translate: TranslateService,
          private userService: UserService) { }
  ngOnInit() {
      console.info( "init DashboardComponent" );
      this.translate.get("userTitle")
      .subscribe((userTitle: any) => {
           this.userTitle = userTitle;
       });
      /*this.sellerAndBuyersChartData = {
              labels: [],
              datasets: []
           };*/
      this.monthNames.forEach( (monthName: any ) => {
           this.translate.get(monthName.i18nMonthName)
           .subscribe((monthNameValue: any) => {
               monthName.monthNameValue = monthNameValue;
           });
       });
      if (this.hasRole(['ADMIN', 'SELLER_AGENT','BUYER_AGENT'])) {
          this.sellerAndBuyersDailyGrowthRate = {
                  labels: [],
                  datasets: []
               };
          this.sellerAndBuyersMonthlyGrowthRate = {
                  labels: [],
                  datasets: []
               };
          this.sellerAndBuyersYearlyGrowthRate = {
                  labels: [],
                  datasets: []
               };
           if (this.hasRole(['ADMIN', 'SELLER_AGENT'])) {
               this.initSellerChartData();
           }
           if (this.hasRole(['ADMIN', 'BUYER_AGENT'])) {
               this.initBuyerChartData();
           }
           
      }

  }
  ngAfterViewInit() {
      setTimeout(()=>{    //<<<---    using ()=> syntax
          if (!this.sellerAndBuyersChartData) {
              this.setSellerAndBuyerDailyGrowthRate(null);
          }
          
     }, 1000);
      
  }
  setSellerAndBuyerDailyGrowthRate(event) {
      this.sellerAndBuyersChartData = this.sellerAndBuyersDailyGrowthRate;
  }
  setSellerAndBuyerMonthlyGrowthRate(event) {
      this.sellerAndBuyersChartData = this.sellerAndBuyersMonthlyGrowthRate;
  }
  setSellerAndBuyerYearlyGrowthRate(event) {
      this.sellerAndBuyersChartData = this.sellerAndBuyersYearlyGrowthRate;
  }
  initSellerChartData() {
      this.userService.sellersDailyGrowthRate(6).subscribe((result: any) => {
              let sellersGrowthData: any[] = [];
              let sellersGrowthDataLabels: any[] = [];
              for (let [key, value] of Object.entries(result)) {
                  sellersGrowthData.push(value);
                  sellersGrowthDataLabels.push(key);
              }
              var sellers = {
                  label: 'Sellers',
                  backgroundColor: '#33cc33',
                  borderColor: '#1E88E5',
                  data: sellersGrowthData
              };
              this.sellerAndBuyersDailyGrowthRate.labels = sellersGrowthDataLabels;
              this.sellerAndBuyersDailyGrowthRate.datasets.push(sellers);
          },
          ( error ) => {
              console.log('Error', error);
          });
      this.userService.sellersMonthlyGrowthRate(6).subscribe((result: any) => {
              let sellersGrowthData: any[] = [];
              let sellersGrowthDataLabels: any[] = [];
              for (let [key, value] of Object.entries(result)) {
                  sellersGrowthData.push(value);
                  sellersGrowthDataLabels.push(key);
              }
              var sellers = {
                  label: 'Sellers',
                  backgroundColor: '#33cc33',
                  borderColor: '#1E88E5',
                  data: sellersGrowthData
              };
              let monthIndex: number = this.getMonthIndex(sellersGrowthData);
              var monthNamesForData = [];
              for (var i = 0; i < sellersGrowthData.length; i++) {
                  var monthName = this.monthNames[monthIndex].monthNameValue;
                  monthNamesForData.push(monthName);
                  monthIndex = monthIndex + 1;
                  if (monthIndex == 12) {
                      monthIndex = 0;
                  }
              }
              this.sellerAndBuyersMonthlyGrowthRate.labels = monthNamesForData;
              this.sellerAndBuyersMonthlyGrowthRate.datasets.push(sellers);
          },
          ( error ) => {
              console.log('Error', error);
          });
      this.userService.sellersYearlyGrowthRate(6).subscribe((result: any) => {
              let sellersGrowthData: any[] = [];
              let sellersGrowthDataLabels: any[] = [];
              for (let [key, value] of Object.entries(result)) {
                  sellersGrowthData.push(value);
                  sellersGrowthDataLabels.push(key);
              }
              var sellers = {
                  label: 'Sellers',
                  backgroundColor: '#33cc33',
                  borderColor: '#1E88E5',
                  data: sellersGrowthData
              };
              this.sellerAndBuyersYearlyGrowthRate.labels = sellersGrowthDataLabels;
              this.sellerAndBuyersYearlyGrowthRate.datasets.push(sellers);
          },
          ( error ) => {
              console.log('Error', error);
          });
  }
  initBuyerChartData() {
      this.userService.buyersDailyGrowthRate(6).subscribe((result: any) => {
          let buyersGrowthData: any[] = [];
          let buyersGrowthDataLabels: any[] = [];
          for (let [key, value] of Object.entries(result)) {
              buyersGrowthData.push(value);
              buyersGrowthDataLabels.push(key);
          }
          var buyers = {
                  label: 'Buyers',
                  backgroundColor: '#ffff00',
                  borderColor: '#7CB342',
                  data: buyersGrowthData
          };
          this.sellerAndBuyersDailyGrowthRate.labels = buyersGrowthDataLabels;
          this.sellerAndBuyersDailyGrowthRate.datasets.push(buyers);
          /*if (!this.sellerAndBuyersChartData) {
              this.sellerAndBuyersChartData = this.sellerAndBuyersDailyGrowthRate;
          }*/
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.buyersMonthlyGrowthRate(6).subscribe((result: any) => {
          let buyersGrowthData: any[] = [];
          let buyersGrowthDataLabels: any[] = [];
          for (let [key, value] of Object.entries(result)) {
              buyersGrowthData.push(value);
              buyersGrowthDataLabels.push(key);
          }
          var buyers = {
                  label: 'Buyers',
                  backgroundColor: '#ffff00',
                  borderColor: '#7CB342',
                  data: buyersGrowthData
          };
          let monthIndex: number = this.getMonthIndex(buyersGrowthData);
          var monthNamesForData = [];
          for (var i = 0; i < buyersGrowthData.length; i++) {
              var monthName = this.monthNames[monthIndex].monthNameValue;
              monthNamesForData.push(monthName);
              monthIndex = monthIndex + 1;
              if (monthIndex == 12) {
                  monthIndex = 0;
              }
          }
          this.sellerAndBuyersMonthlyGrowthRate.labels = monthNamesForData;
          this.sellerAndBuyersMonthlyGrowthRate.datasets.push(buyers);
      },
      ( error ) => {
          console.log('Error', error);
      });
      this.userService.buyersYearlyGrowthRate(6).subscribe((result: any) => {
          let buyersGrowthData: any[] = [];
          let buyersGrowthDataLabels: any[] = [];
          for (let [key, value] of Object.entries(result)) {
              buyersGrowthData.push(value);
              buyersGrowthDataLabels.push(key);
          }
          var buyers = {
                  label: 'Buyers',
                  backgroundColor: '#ffff00',
                  borderColor: '#7CB342',
                  data: buyersGrowthData
          };
          this.sellerAndBuyersYearlyGrowthRate.labels = buyersGrowthDataLabels;
          this.sellerAndBuyersYearlyGrowthRate.datasets.push(buyers);
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
