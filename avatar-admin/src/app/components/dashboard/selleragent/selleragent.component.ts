import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';
import { Router, ActivatedRoute } from "@angular/router";
import { DomSanitizer } from '@angular/platform-browser';
import { DynamicDialogRef, DialogService } from 'primeng/api';
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "../../../services/auth.service";
import { UserDTO } from "../../../services/authorization/userdto.model";
import { QualityDTO } from "../../../services/product/qualitydto.model";
import { QualityService } from '../../../services/product/qualityservice.generated';
import { FilterCriteria } from "../../../services/product/filtercriteria.model";
import { PriceHistoryService } from '../../../services/product/pricehistoryservice.generated';
import { SellerTransactionDTO } from "../../../services/transaction/sellertransactiondto.model";
import { SellerOrder } from "../../../services/transaction/sellerorder.model";
import { SellerTransactionService } from "../../../services/transaction/sellertransactionservice.generated";
import { SellerOrderComponent } from "./seller-order/seller-order.component";

import * as _ from "lodash";


@Component({
  selector: 'selleragent',
  templateUrl: './selleragent.component.html',
  styleUrls: ['./selleragent.component.css'],
  providers: [DialogService]
})
export class SellerAgentComponent implements OnInit {
    protected numberOfRowsPerPage = 8;
    protected orders: any[];
    protected languageCode: string;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    protected loading: boolean;
    protected userDTO: UserDTO;
    protected qualities: QualityDTO[];
    constructor(private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private priceHistoryService: PriceHistoryService,
            private qualityService: QualityService,
            private dialogService: DialogService, private translate: TranslateService, private domSanitizer: DomSanitizer,
            private router: Router, private activatedRoute: ActivatedRoute) { 
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.info( "OnInit SellerAgent Component tns" );
        this.orders = [];
        this.displayAlertDialog = false;
        this.loading = false;
        this.initQualityList();
    }
    initQualityList() {
        this.qualityService.getAllExceptDeleted().subscribe((qualities: QualityDTO[]) => {
            this.qualities = qualities;
        },
        ( error ) => {
            this.showAlertDialog('Error', 'Error while getting Quality List');
        });
    }
    lazyLoadRecordList(event?: any) {
        this.showLoading(true);
        var date = new Date();
        date.setHours(0, 0, 0, 0);
        this.sellerTransactionService.getOrdersForSellerAgent(this.userDTO['id'], date.toISOString())
            .subscribe((sellerOrders: SellerOrder[]) => {
            this.orders = sellerOrders;
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Seller Orders');
        });
    }

    protected showLoading(value: boolean) {
        this.loading = value;
    }
    protected showDetailDialog(price: any) {
        let ref: DynamicDialogRef = this.dialogService.open(SellerOrderComponent, {
            data: {
                price: price, qualities: this.qualities
            },
            header: price.product[this.languageCode],
            width: '50%',
            height: '70%'
        });
        ref.onClose.subscribe((saved: boolean) => {
            if (saved) {
                this.showAlertDialog('Success', 'Successfully Saved');
                this.lazyLoadRecordList();
            }
        });
    }
    protected showAlertDialog(title: string, message: string) {
        this.displayAlertDialog = true;
        this.alertDialogTitle = title;
        this.alertDialogMessage = message;
    }
    hasPhoto(assets: any[]) {
        return assets && assets.length > 0;
    }
    getPhoto(assets: any[]) {
        var assetValue = assets[0]['assetValue'];
        return this.domSanitizer.bypassSecurityTrustUrl(assetValue);
    }
}
