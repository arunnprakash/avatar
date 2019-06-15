import { SmsProcessedStatusDTO } from './smsprocessedstatusdto.model';

export class OutgoingSmsDTO {
    notificationStatus: SmsProcessedStatusDTO;
    mobileNumber: string;
    smsContent: string;
}
