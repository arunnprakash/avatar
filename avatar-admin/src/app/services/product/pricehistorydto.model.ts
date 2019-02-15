import { AssetTypeDTO } from './assettypedto.model';
import { AssetDTO } from './assetdto.model';
import { ProductDTO } from './productdto.model';
import { QualityDTO } from './qualitydto.model';

export class PriceHistoryDTO {
    product: ProductDTO;
    price: number;
    quality: QualityDTO;
}
