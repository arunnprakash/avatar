import { SmsProcessedStatusDTO } from './smsprocessedstatusdto.model';

export class IncomingSmsDTO {
    notificationStatus: SmsProcessedStatusDTO;
    mobileNumber: string;
    smsContent: string;
}
