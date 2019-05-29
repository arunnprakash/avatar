import { RoleDTO } from './roledto.model';
import { UserDTO } from './userdto.model';

export class LoginResponse {
    accessToken: string;
    userDTO: UserDTO;
}
