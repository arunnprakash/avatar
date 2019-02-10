import { ProductDTO } from './productdto.model';

export class ProductRegionDTO {
    productId: ProductDTO;
    state: number;
    district: number;
    taluk: number;
}
