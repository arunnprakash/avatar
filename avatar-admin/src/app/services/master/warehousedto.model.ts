import { DistrictDTO } from './districtdto.model';
import { StateDTO } from './statedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';
import { MarketDTO } from './marketdto.model';

export class WareHouseDTO {
    latitude: string;
    longitude: string;
    address: string;
    taluk: TalukDTO;
    market: MarketDTO;
}
