import { AssetTypeDTO } from './assettypedto.model';
import { AssetDTO } from './assetdto.model';
import { ProductDTO } from './productdto.model';

export class ProductAssetDTO {
    productId: ProductDTO;
    assetId: AssetDTO;
}
