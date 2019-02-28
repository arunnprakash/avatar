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
    protected displayAlertDialog: boolean;
    protected alertDialogTitle: string;
    protected alertDialogMessage: string;
    protected saved: boolean;
    protected userDTO: UserDTO;
    constructor(private userService: UserService, private authService: AuthService, 
            private sellerTransactionService: SellerTransactionService,
            private params: DynamicDialogConfig, private dialog: DynamicDialogRef ) {
        this.price = params.data.price;
        this.qualities = params.data.qualities;
        this.quality = this.qualities[0];
        this.userDTO = authService.getUserInfo();
        this.languageCode = this.userDTO.preferredLanguage.languageCode;
    }

    ngOnInit() {
        console.log("ngOnInit SellProductComponent.ts");
        this.loading = false;
        this.displayAlertDialog = false;
        this.quantity = 1;
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
            this.showAlertDialog('Success', 'Successfully Saved. Your Agent AgentName contact You.');
        },
        ( error ) => {
            this.showLoading(false);
            this.showAlertDialog('Error', 'Error while Saving ');
        });
    }
}
