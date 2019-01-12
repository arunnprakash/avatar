import { TranslateLoader } from "@ngx-translate/core";
import { knownFolders } from "tns-core-modules/file-system";
import { map } from "rxjs/operators";
//import { fromPromise } from 'rxjs/observable/fromPromise';
import { Observable, from , of } from 'rxjs';

export class NSNgxTranslateLoader extends TranslateLoader {
    prefix = "./assets/i18n/";
    suffix = ".json";

    constructor(prefix?: string, suffix?: string) {
        super();
        this.prefix = prefix;
        this.suffix = suffix;
    }

    getTranslation(lang: string): Observable<any> {
        from(knownFolders.currentApp().getFile(`${this.prefix}${lang}${this.suffix}`).readText()).pipe(
                map((data: any) => console.log("Json String :: " + data))
            );
        return from(knownFolders.currentApp().getFile(`${this.prefix}${lang}${this.suffix}`).readText()).pipe(
            map((data: any) => JSON.parse(data))
        );
    }
}