import { Component, OnInit, AfterViewInit } from '@angular/core';
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";
import * as application from "tns-core-modules/application";
import * as permissions from "nativescript-permissions";
import * as TNSInbox from 'nativescript-sms-inbox';
import * as TNSPhone from 'nativescript-phone';
import { HttpClient, HttpParams, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { SendSmsDTO } from './outgoingsmsdto.model';
import { ReceivedSmsDTO } from './incomingsmsdto.model';
import * as _ from "lodash";

declare let android: any

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    private readingInbox: boolean = false;
    private checkingSendSms: boolean = false;
    smsGatewayBaseUrl: string = "http://localhost:2050";
    private smsManager: any;
    private reqid: number;
    private started: boolean = false;
    constructor(private httpClient: HttpClient) { }
    
    ngOnInit() {
        
        permissions.requestPermission(android.Manifest.permission.SEND_SMS, "This App Need Access for Send Sms")
        .then( (res: any) => {
            console.log("SEND_SMS PERMISSION GRANTED");
            this.smsManager = android.telephony.SmsManager.getDefault();
        })
        .catch( (err) => {
            console.log("SEND_SMS PERMISSION DENIED");
        });
    }
     ngAfterViewInit() {
        /*setInterval(()=> { 
            if (this.started) { this.readInbox(); } 
        }, 4000);*/
        setInterval(()=> { 
            if (this.started) { this.checkSendSms(); }
        }, 4000);
    }
    start(){
        this.started = true;
    }
    stop() {
        this.started = false;
    }
    readInbox() {
        if (!this.readingInbox) {
            this.readingInbox = true;
            let fromNumber: string = "9884850965";
            TNSInbox.getInboxesFromNumber(fromNumber, { max: 10 }).then((res: any) => {
                console.log("Inbox", JSON.stringify(res));
                let receivedSmsDTO: ReceivedSmsDTO = new ReceivedSmsDTO();
                receivedSmsDTO.message = JSON.stringify(res);
                this.pushToSmsGateway(receivedSmsDTO).subscribe((result: any) => {
                    console.log("Received Sms pushed to Offline Service", result);
                    this.readingInbox = false;
                });
            }, (err) => {
                console.log('Error: ' + err);
                this.readingInbox = false;
            });
        }
    }
    
    checkSendSms() {
        if (!this.checkingSendSms && this.smsManager) {
            this.checkingSendSms = true;
            console.log("checkingSendSms", this.checkingSendSms);
            this.getSendSmsList().subscribe((sendSmsList: SendSmsDTO[]) => {
                console.log("SmsList for send", sendSmsList);
                var numberOfSms = sendSmsList.length;
                var smsSendCompleted = 0;
                _.each(sendSmsList, (sendSms: SendSmsDTO ) => {
                    _.each(sendSms.receipients, (receipient: string) => {
                        this.smsManager.sendTextMessage(receipient, null, sendSms.message, null, null);
                    });
                    console.log("SMS Send Successfully");
                    ++smsSendCompleted;
                    if (numberOfSms == smsSendCompleted) {
                        this.sendSmsCompleted(sendSmsList).subscribe((ack: any) => {
                            console.log("Send Sms Completed Ack Done");
                            this.checkingSendSms = false;
                        }, (err) => {
                            console.error('Send Sms Completed Ack Error', err);
                            this.checkingSendSms = false;
                        });
                    }
                    /*TNSPhone.sms(sendSms.receipients, sendSms.message)
                    .then((args) => {
                        console.log("SMS Send Successfully");
                        ++smsSendCompleted;
                        if (numberOfSms == smsSendCompleted) {
                            this.sendSmsCompleted(sendSmsList).subscribe((ack: any) => {
                                console.log("Send Sms Completed Ack Done");
                                this.checkingSendSms = false;
                            }, (err) => {
                                console.error('Send Sms Completed Ack Error', err);
                                this.checkingSendSms = false;
                            });
                        }
                    }, (err) => {
                        console.error('SMS Sending Error', err);
                        ++smsSendCompleted;
                        if (numberOfSms == smsSendCompleted) {
                            this.checkingSendSms = false;
                        }
                    });*/
                });
                
            },
            ( error ) => {
               console.error('Error', 'Error while getting SendSmsList List');
               this.checkingSendSms = false;
            });
        }
    }
    
    sendSmsCompleted(sendSmsList: SendSmsDTO[]): Observable<any> {
        const url = this.smsGatewayBaseUrl + '/send-sms-completed';
        return this.httpClient.post<string>(url, sendSmsList)
            .pipe(catchError(this.handleError));
    }
    
    pushToSmsGateway(receivedSmsDTO: ReceivedSmsDTO): Observable<any> {
        const url = this.smsGatewayBaseUrl + '/receive-sms';
        return this.httpClient.post<string>(url, receivedSmsDTO)
            .pipe(catchError(this.handleError));
    }
    
    getSendSmsList(): Observable<SendSmsDTO[]> {
        const url = 'http://192.168.1.181:2050/get-send-sms-list';
        return this.httpClient.get<SendSmsDTO[]>(url)
            .pipe(catchError(this.handleError));
    }
    
    handleError(error: HttpErrorResponse) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        //this.log('error', error);
    
        return throwError(error);
    }
}
