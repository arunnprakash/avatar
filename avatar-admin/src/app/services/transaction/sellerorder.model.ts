import { SellerTransactionStatusDTO } from './sellertransactionstatusdto.model';
import { SellerTransactionDTO } from './sellertransactiondto.model';

export class SellerOrder {
    sellerTransaction: SellerTransactionDTO;
    seller: { [index: string]: any };
    sellerAgent: { [index: string]: any };
    priceTag: { [index: string]: any };
}
