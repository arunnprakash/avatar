import { RoleDTO } from './roledto.model';
import { StateDTO } from './statedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';
import { VillageDTO } from './villagedto.model';
import { DistrictDTO } from './districtdto.model';
import { LanguageDTO } from './languagedto.model';
import { GenderDTO } from './genderdto.model';

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
    village: VillageDTO;
    gender: GenderDTO;
}
