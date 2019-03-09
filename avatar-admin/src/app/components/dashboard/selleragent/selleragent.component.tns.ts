import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DrawerTransitionBase, RadSideDrawer, SlideInOnTopTransition } from "nativescript-ui-sidedrawer";
import { RadSideDrawerComponent, SideDrawerType } from "nativescript-ui-sidedrawer/angular";
import { Page } from "tns-core-modules/ui/page";
import { ModalDialogService, ModalDialogOptions } from "nativescript-angular/modal-dialog";
import { LoadingIndicator } from "nativescript-loading-indicator";
import { fromBase64, fromResource }  from "tns-core-modules/image-source";
import { Router, ActivatedRoute } from "@angular/router";
import { ViewContainerRef } from "@angular/core";
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
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

import { ObservableArray } from "tns-core-modules/data/observable-array";
import * as _ from "lodash";

@Component( {
    selector: 'selleragent',
    templateUrl: './selleragent.component.html',
    styleUrls: ['./selleragent.component.css'],
    providers: [ModalDialogService]
} )
export class SellerAgentComponent implements OnInit {
    public sideDrawerTransition: DrawerTransitionBase;
    private drawer: RadSideDrawer;
    private loadingIndicator: LoadingIndicator;
    private loadingIndicatorOptions: any;
    protected orders: any[];
    protected languageCode: string;
    protected userDTO: UserDTO;
    protected qualities: QualityDTO[];
    constructor(private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private priceHistoryService: PriceHistoryService,
            private qualityService: QualityService,
            private router: Router, private activatedRoute: ActivatedRoute,
            private vcRef: ViewContainerRef, private dialogService: ModalDialogService) { 
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.info( "On Init SellerAgent Component" );
        this.loadingIndicator = new LoadingIndicator();
        this.loadingIndicatorOptions = {
                message: 'Loading...',
                progress: 0.65,
                android: {
                  indeterminate: true,
                  cancelable: true,
                  cancelListener: function(dialog) { console.log("Loading cancelled") },
                  max: 100,
                  progressNumberFormat: "%1d/%2d",
                  progressPercentFormat: 0.53,
                  progressStyle: 1,
                  secondaryProgress: 1
                }
              };
        this.initQualityList();
        this.lazyLoadRecordList();
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
        date.setHours(24, 0, 0, 0);
        this.sellerTransactionService.getOrdersForSellerAgent(this.userDTO['id'], date.toISOString())
            .subscribe((results: SellerOrder[]) => {
                let sellerOrders: SellerOrder[] = [];
                results.forEach( (sellerOrder: any ) => {
                    if (!sellerOrder.sellerTransaction.sellerAgentProductQuality) {
                        sellerOrders.push(sellerOrder);
                    }
                });
            this.orders = sellerOrders;
            this.showLoading(false);
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while getting Seller Orders');
        });
    }
    protected showDetailDialog(sellerOrder: any) {
        const options: any = {
                viewContainerRef: this.vcRef,
                context: { sellerOrder: sellerOrder, qualities: this.qualities },
                fullscreen: false,
        };
        let ref: Promise<any> = this.dialogService.showModal(SellerOrderComponent, options);
        ref.then(result => {
            if (result) {
                this.lazyLoadRecordList();
            }
        }).catch(error => {console.log(error);});
    }
    protected showAlertDialog(title: string, message: string) {
        const alertOptions: AlertOptions = {
            title: title,
            message: message,
            okButtonText: "OK",
            cancelable: false // [Android only] Gets or sets if the dialog can be canceled by taping outside of the dialog.
        };

        alert(alertOptions).then(() => {
            console.log("Dialog closed!");
        });
    }
    protected showLoading(value: boolean) {
        if (value) {
            this.loadingIndicator.show(this.loadingIndicatorOptions);
        } else {
            this.loadingIndicator.hide();
        }
    }
    hasPhoto(assets: any[]) {
        return assets && assets.length > 0;
    }
    getPhoto(assets: any[]) {
        var assetValue = assets[0]['assetValue'];
        assetValue = assetValue.replace(/^data:image\/[a-z]+;base64,/, "");
        return fromBase64(assetValue);
    }
}
