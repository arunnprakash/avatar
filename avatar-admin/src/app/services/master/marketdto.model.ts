import { DistrictDTO } from './districtdto.model';
import { StateDTO } from './statedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';

export class MarketDTO {
    latitude: string;
    longitude: string;
    address: string;
    taluk: TalukDTO;
}
