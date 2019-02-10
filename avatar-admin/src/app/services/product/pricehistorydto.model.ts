import { ProductDTO } from './productdto.model';
import { QualityDTO } from './qualitydto.model';

export class PriceHistoryDTO {
    productId: ProductDTO;
    price: number;
    qualityId: QualityDTO;
}
