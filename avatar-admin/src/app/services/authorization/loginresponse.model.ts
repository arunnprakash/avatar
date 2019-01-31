import { RoleDTO } from './roledto.model';
import { DistrictDTO } from './districtdto.model';
import { StateDTO } from './statedto.model';
import { AssetDTO } from './assetdto.model';
import { LanguageDTO } from './languagedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';
import { UserDTO } from './userdto.model';
import { AssetTypeDTO } from './assettypedto.model';
import { GenderDTO } from './genderdto.model';
import { VillageDTO } from './villagedto.model';

export class LoginResponse {
    accessToken: string;
    userDTO: UserDTO;
}
