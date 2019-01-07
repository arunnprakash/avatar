import { ZoneId } from './zoneid.model';
import { ZoneOffset } from './zoneoffset.model';

export class ZonedDateTime {
    offset: ZoneOffset;
    zone: ZoneId;
}
