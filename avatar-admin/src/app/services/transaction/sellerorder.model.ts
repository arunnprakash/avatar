import { SellerTransactionStatusDTO } from './sellertransactionstatusdto.model';
import { SellerTransactionDTO } from './sellertransactiondto.model';

export class SellerOrder {
    sellerTransaction: SellerTransactionDTO;
    priceTag: { [index: string]: any };
}
