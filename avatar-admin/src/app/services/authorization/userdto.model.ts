import { RoleDTO } from './roledto.model';

export class UserDTO {
    userName: string;
    firstName: string;
    lastName: string;
    mobileNumber: string;
    suspended: boolean;
    latitude: string;
    longitude: string;
    roles: RoleDTO[];
}
