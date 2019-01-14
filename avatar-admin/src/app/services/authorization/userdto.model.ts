import { RoleDTO } from './roledto.model';
import { LanguageDTO } from './languagedto.model';

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
    preferredLanguage: LanguageDTO;
}
