import { RoleDTO } from './roledto.model';
import { UserDTO } from './userdto.model';

export class SellerAgentWareHouseMappingDTO {
    sellerAgent: UserDTO;
    wareHouse: { [index: string]: any };
}
