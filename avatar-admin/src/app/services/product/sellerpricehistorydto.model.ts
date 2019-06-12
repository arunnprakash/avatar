import { AssetDTO } from './assetdto.model';
import { AssetTypeDTO } from './assettypedto.model';
import { MarketPriceDTO } from './marketpricedto.model';
import { ProductDTO } from './productdto.model';
import { QualityDTO } from './qualitydto.model';

export class SellerPriceHistoryDTO {
    marketPrice: MarketPriceDTO;
    product: ProductDTO;
    price: number;
    quality: QualityDTO;
    sellerAgentCommission: { [index: string]: any };
    sellerTransportationCharge: { [index: string]: any };
    sellerMerchantCommission: { [index: string]: any };
}
