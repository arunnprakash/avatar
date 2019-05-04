import { Component, OnInit } from '@angular/core';
import { LoadingIndicator } from "nativescript-loading-indicator";
import { prompt, PromptOptions, PromptResult } from "tns-core-modules/ui/dialogs";
import { confirm, ConfirmOptions } from "tns-core-modules/ui/dialogs";
import { alert, AlertOptions } from "tns-core-modules/ui/dialogs";
import { ModalDialogService, ModalDialogOptions, ModalDialogParams } from "nativescript-angular/modal-dialog";
import { isAndroid } from "tns-core-modules/platform";

import { AuthService } from "../../../../services/auth.service";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { UserService } from "../../../../services/authorization/userservice.generated";
import { QualityDTO } from "../../../../services/product/qualitydto.model";
import { SellerTransactionDTO } from "../../../../services/transaction/sellertransactiondto.model";
import { SellerTransactionService } from "../../../../services/transaction/sellertransactionservice.generated";

import * as _ from "lodash";

@Component({
  selector: 'seller-truckdriver-order',
  templateUrl: './seller-truckdriver-order.component.html',
  styleUrls: ['./seller-truckdriver-order.component.css']
})
export class SellerTruckDriverOrderComponent implements OnInit {
    protected sellerOrder: any;
    protected quality: QualityDTO;
    protected quantity: number;
    protected qualities: QualityDTO[];
    
    protected languageCode: string;
    protected userDTO: UserDTO;

    private loadingIndicator: LoadingIndicator;
    private loadingIndicatorOptions: any;
    protected saved: boolean;

    constructor(private userService: UserService, private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private params: ModalDialogParams ) {
        this.sellerOrder = params.context.sellerOrder;
        this.qualities = params.context.qualities;
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.log("ngOnInit SellProductComponent.ts");
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
        this.saved = false;
        this.quantity = this.sellerOrder.sellerTransaction.sellerProductQuantity;
        this.quality = _.find(this.qualities, { 'id': this.sellerOrder.sellerTransaction.sellerProductQuality });
    }
    onLoaded(event) {
        if (isAndroid && event.object._dialogFragment) {
            event.object._dialogFragment.getDialog().setCanceledOnTouchOutside(false);
        }
    }
    selectQuality(qualityId: number) {
        this.quality = _.find(this.qualities, {'id': qualityId});
    }
    protected closeDetailDialog() {
        this.params.closeCallback(this.saved);
    }
    protected showLoading(value: boolean) {
        if (value) {
            this.loadingIndicator.show(this.loadingIndicatorOptions);
        } else {
            this.loadingIndicator.hide();
        }
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
    protected createTransaction() {
        let sellerTransaction: SellerTransactionDTO = this.sellerOrder.sellerTransaction;
        sellerTransaction.product = this.sellerOrder.priceTag.product['id'];
        sellerTransaction.sellerAgentProductQuality = this.quality['id'];
        sellerTransaction.sellerAgentProductQuantity = this.quantity;
        sellerTransaction.sellerAgent = this.userDTO['id'];
        this.showLoading(true);
        this.sellerTransactionService.update(sellerTransaction)
        .subscribe((model: any) => {
            this.showLoading(false);
            this.saved = true;
            this.closeDetailDialog();
            this.showAlertDialog('Success', 'Successfully Saved. Your Agent QC contact You.');
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while Saving ');
        });
    }
}
