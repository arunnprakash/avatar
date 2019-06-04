import { RoleDTO } from './roledto.model';
import { UserDTO } from './userdto.model';

export class QcWareHouseMappingDTO {
    qc: UserDTO;
    wareHouse: { [index: string]: any };
}
