import { DistrictDTO } from './districtdto.model';
import { StateDTO } from './statedto.model';
import { BankNameDTO } from './banknamedto.model';
import { CountryDTO } from './countrydto.model';
import { TalukDTO } from './talukdto.model';
import { VillageDTO } from './villagedto.model';

export class BankDTO {
    ifscCode: string;
    village: VillageDTO;
    bankName: BankNameDTO;
}
