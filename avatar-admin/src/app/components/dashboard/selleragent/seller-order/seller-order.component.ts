import { Component, OnInit } from '@angular/core';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { AuthService } from "../../../../services/auth.service";
import { UserDTO } from "../../../../services/authorization/userdto.model";
import { UserService } from "../../../../services/authorization/userservice.generated";
import { QualityDTO } from "../../../../services/product/qualitydto.model";
import { SellerTransactionDTO } from "../../../../services/transaction/sellertransactiondto.model";
import { SellerTransactionService } from "../../../../services/transaction/sellertransactionservice.generated";

import * as _ from "lodash";

@Component({
  selector: 'seller-order',
  templateUrl: './seller-order.component.html',
  styleUrls: ['./seller-order.component.css']
})
export class SellerOrderComponent implements OnInit {
    protected sellerOrder: any;
    protected quality: QualityDTO;
    protected quantity: number;
    protected qualities: QualityDTO[];
    
    protected languageCode: string;
    protected loading: boolean;
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    protected saved: boolean;
    protected userDTO: UserDTO;
    constructor(private userService: UserService, private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private params: DynamicDialogConfig, private dialog: DynamicDialogRef ) {
        this.sellerOrder = params.data.sellerOrder;
        this.qualities = params.data.qualities;
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.log("ngOnInit SellProductComponent.ts");
        this.loading = false;
        this.displayAlertDialog = false;
        this.quantity = this.sellerOrder.sellerTransaction.sellerProductQuantity;
        this.quality = _.find(this.qualities, { 'id': this.sellerOrder.sellerTransaction.sellerProductQuality });
        this.saved = false;
    }

    protected showLoading(value: boolean) {
        this.loading = value;
    }
    protected showAlertDialog(title: string, message: string) {
        this.displayAlertDialog = true;
        this.alertDialogTitle = title;
        this.alertDialogMessage = message;
    }
    protected closeDetailDialog() {
        this.dialog.close(this.saved);
    }
    filterAutoCompleteSuggestion(event) {
        let temp: QualityDTO[] = [];
        this.qualities.forEach( (quality: QualityDTO ) => {
            temp.push(quality);
        });
        this.qualities = temp;
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
