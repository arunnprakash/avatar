import { RoleDTO } from './roledto.model';
import { ZoneId } from './zoneid.model';
import { ZonedDateTime } from './zoneddatetime.model';
import { UserDTO } from './userdto.model';
import { ZoneOffset } from './zoneoffset.model';

export class LoginResponse {
    accessToken: string;
    userDTO: UserDTO;
}
