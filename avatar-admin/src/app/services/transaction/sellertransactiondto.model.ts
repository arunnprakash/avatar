import { SellerTransactionStatusDTO } from './sellertransactionstatusdto.model';

export class SellerTransactionDTO {
    transactionId: string;
    transactionStatus: SellerTransactionStatusDTO;
    product: number;
    sellerProductQuality: number;
    sellerProductQuantity: number;
    seller: number;
    sellerAgentProductQuality: number;
    sellerAgentProductQuantity: number;
    sellerAgent: number;
    truckDriver: number;
    wareHouse: number;
    wareHouseProductQuantity: number;
    quantityCheckedBy: number;
    wareHouseProductQuality: number;
    qualityCheckedBy: number;
}
