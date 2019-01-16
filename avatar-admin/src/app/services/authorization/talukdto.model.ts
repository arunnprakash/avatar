import { DistrictDTO } from './districtdto.model';
import { StateDTO } from './statedto.model';
import { CountryDTO } from './countrydto.model';

export class TalukDTO {
    talukCode: string;
    district: DistrictDTO;
}
