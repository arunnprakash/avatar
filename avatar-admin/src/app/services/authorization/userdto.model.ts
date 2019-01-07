import { RoleDTO } from './roledto.model';
import { ZoneId } from './zoneid.model';
import { ZonedDateTime } from './zoneddatetime.model';
import { ZoneOffset } from './zoneoffset.model';

export class UserDTO {
    userName: string;
    firstName: string;
    lastName: string;
    mobileNumber: string;
    dob: ZonedDateTime;
    suspended: boolean;
    latitude: string;
    longitude: string;
    roles: RoleDTO[];
}
