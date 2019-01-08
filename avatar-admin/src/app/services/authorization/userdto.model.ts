import { RoleDTO } from './roledto.model';

export class UserDTO {
    userName: string;
    firstName: string;
    lastName: string;
    mobileNumber: string;
    dob: Date;
    suspended: boolean;
    latitude: string;
    longitude: string;
    roles: RoleDTO[];
}
