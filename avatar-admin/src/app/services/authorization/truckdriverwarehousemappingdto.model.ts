import { RoleDTO } from './roledto.model';
import { UserDTO } from './userdto.model';

export class TruckDriverWareHouseMappingDTO {
    truckDriver: UserDTO;
    wareHouse: { [index: string]: any };
}
