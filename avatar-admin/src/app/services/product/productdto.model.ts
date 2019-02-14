import { AssetTypeDTO } from './assettypedto.model';
import { AssetDTO } from './assetdto.model';

export class ProductDTO {
    productCode: string;
    assets: AssetDTO[];
}
