
export class BaseDTO {
    id: number;
    createdBy: string;
    createdDate: Date;
    lastModifiedBy: string;
    lastModifiedDate: Date;
    deleted: boolean;
    version: number;
}
