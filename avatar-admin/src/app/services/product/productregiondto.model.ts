import { AssetDTO } from './assetdto.model';
import { AssetTypeDTO } from './assettypedto.model';
import { ProductDTO } from './productdto.model';

export class ProductRegionDTO {
    product: ProductDTO;
    state: number;
    district: number;
    taluk: number;
}
