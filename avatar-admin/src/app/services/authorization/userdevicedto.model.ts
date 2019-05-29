import { RoleDTO } from './roledto.model';
import { UserDTO } from './userdto.model';

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
