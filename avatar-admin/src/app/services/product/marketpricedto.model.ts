import { AssetDTO } from './assetdto.model';
import { AssetTypeDTO } from './assettypedto.model';
import { ProductDTO } from './productdto.model';
import { QualityDTO } from './qualitydto.model';

export class MarketPriceDTO {
    market: { [index: string]: any };
    product: ProductDTO;
    price: number;
    quality: QualityDTO;
}
