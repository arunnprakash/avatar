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
  selector: 'sell-product',
  templateUrl: './sell-product.component.html',
  styleUrls: ['./sell-product.component.css']
})
export class SellProductComponent implements OnInit {
    protected price: any;
    protected quality: QualityDTO;
    protected quantity: number;
    protected qualities: QualityDTO[];
    
    protected languageCode: string;
    protected loading: boolean;
    private loadingIndicator: LoadingIndicator;
    private loadingIndicatorOptions: any;
    protected saved: boolean;
    protected userDTO: UserDTO;
    constructor(private userService: UserService, private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private params: ModalDialogParams ) {
        this.price = params.context.price;
        this.qualities = params.context.qualities;
        this.quality = this.qualities[0];
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.log("ngOnInit SellProductComponent.tns.ts");
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
        this.quantity = 1;
        this.saved = false;
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
        console.log("Quantity", this.quantity);
        let sellerTransaction: SellerTransactionDTO = new SellerTransactionDTO();
        sellerTransaction.product = this.price.product['id'];
        sellerTransaction.sellerProductQuality = this.quality['id'];
        sellerTransaction.sellerProductQuantity = this.quantity;
        sellerTransaction.seller = this.userDTO['id'];
        this.showLoading(true);
        this.sellerTransactionService.save(sellerTransaction)
        .subscribe((model: any) => {
            this.showLoading(false);
            this.saved = true;
            this.closeDetailDialog();
            this.showAlertDialog('Success', 'Intent Saved Successfully. Your Agent will Contact You Soon.');
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while Saving ');
        });
    }
}
