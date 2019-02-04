import { RoleDTO } from './roledto.model';
import { StateDTO } from './statedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';
import { UserDTO } from './userdto.model';
import { AssetTypeDTO } from './assettypedto.model';
import { VillageDTO } from './villagedto.model';
import { DistrictDTO } from './districtdto.model';
import { AssetDTO } from './assetdto.model';
import { LanguageDTO } from './languagedto.model';
import { GenderDTO } from './genderdto.model';

export class UserDeviceDTO {
    user: UserDTO;
    modelName: string;
    modelType: string;
    os: string;
    osVersion: string;
    sdkVersion: string;
    language: string;
    manufacturer: string;
    uuid: string;
    screenScale: string;
    screenWidth: string;
    screenHeight: string;
    loggedIn: boolean;
}
