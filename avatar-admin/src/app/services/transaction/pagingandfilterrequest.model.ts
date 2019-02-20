import { FilterCriteria } from './filtercriteria.model';

export class PagingAndFilterRequest {
    pageNumber: number;
    pageSize: number;
    filters: FilterCriteria[];
    sortBy: string;
    sortingOrder: string;
}
