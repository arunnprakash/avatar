import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Page } from "tns-core-modules/ui/page";
import { device, screen, isAndroid, isIOS } from "tns-core-modules/platform";
import * as application from "tns-core-modules/application";

import { UserService } from '../../services/authorization/userservice.generated';
import { UserDTO } from "../../services/authorization/userdto.model";
import { LoginRequest } from "../../services/authorization/loginrequest.model";
import { LoginResponse } from "../../services/authorization/loginresponse.model";
import { AuthService } from "../../services/auth.service";
import { TranslateService } from '@ngx-translate/core';
declare let android: any;
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    username: string;
    loginResponse: LoginResponse;
    loginProgress: boolean;
    loginError: boolean;
  constructor(private userService: UserService, private authService: AuthService, 
          private translate: TranslateService, private router: Router, private page: Page) { }

  ngOnInit() {
      console.log("ngOnInit LoginComponent tns");
      this.page.actionBarHidden = true;
      this.page.actionBar.isCollapsed = true;
      this.addAndriodPermissionResultEventListener();
      this.requestReadPhoneStatePermission();
      this.requestAccessFineLocationPermission();

      if (this.authService.isLogged()) {
          this.setPreferredLanguage(this.authService.getUserInfo().preferredLanguage['languageCode']);
          console.info("Already Logged in So Login Navigate to Home");
          this.router.navigate(["/home"]).then( (e) => {
              if (e) {
                  console.log("Navigation to Home is successful!");
              } else {
                  console.log("Navigation to Home has failed!");
              }
          });
      }
  }
  addAndriodPermissionResultEventListener() {
      application.android.addEventListener(application.AndroidApplication.activityRequestPermissionsEvent, (args: any) => {
          if ((<any>args).permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {
              // removeEventListener to reduce memory usage since it doesnt need to listen anymore
              //application.android.removeEventListener(application.AndroidApplication.activityRequestPermissionsEvent, onPermissionsEvent)
              if ((<any>args).grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                  console.log("ACCESS_FINE_LOCATION PERMISSION GRANTED");
                  //Initiate OTP Request
              } else {
                  console.log("ACCESS_FINE_LOCATION PERMISSION DENIED");
                  // reject(new Error("Permission DENIED for android.permission.ACCESS_FINE_LOCATION"))
              }
          }
          if ((<any>args).permissions[0] == android.Manifest.permission.READ_PHONE_STATE) {
              // removeEventListener to reduce memory usage since it doesnt need to listen anymore
              //application.android.removeEventListener(application.AndroidApplication.activityRequestPermissionsEvent, onPermissionsEvent)
              if ((<any>args).grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                  console.log("READ_PHONE_STATE PERMISSION GRANTED");
                  this.setPhoneNumberAsUserName();
                  //Initiate OTP Request
              } else {
                  console.log("READ_PHONE_STATE PERMISSION DENIED");
                  // reject(new Error("Permission DENIED for android.permission.READ_PHONE_STATE"))
              }
          }
      });
  }
  requestAccessFineLocationPermission() {
      let doesHavePermission: boolean = (
              android.content.pm.PackageManager.PERMISSION_GRANTED
              ==
              android.support.v4.content.ContextCompat.checkSelfPermission(application.android.context, android.Manifest.permission.ACCESS_FINE_LOCATION)
          );
	  console.log("App Have ACCESS_FINE_LOCATION Permission ", doesHavePermission);
	  if (!doesHavePermission) {
          let reqid: number = Math.floor(Math.random() * 999);
	      if (application.android.foregroundActivity) {
	          console.log("Request Permission for Access Fine Location using Foreground activity");
	          android.support.v4.app.ActivityCompat.requestPermissions(application.android.foregroundActivity, [android.Manifest.permission.ACCESS_FINE_LOCATION], reqid);
	      } else if (application.android.startActivity) {
	          console.log("Request Permission for Access Fine Location using startActivity");
	          android.support.v4.app.ActivityCompat.requestPermissions(application.android.startActivity, [android.Manifest.permission.ACCESS_FINE_LOCATION], reqid);
	      } else {
	          
	      }
	  }
  }
  requestReadPhoneStatePermission() {
      // Interesting, this actually works on API less than 23 and will return false if the manifest permission was forgotten
      let doesHavePermission: boolean = (
          android.content.pm.PackageManager.PERMISSION_GRANTED
          ==
          android.support.v4.content.ContextCompat.checkSelfPermission(application.android.context, android.Manifest.permission.READ_PHONE_STATE)
      );
      console.log("App Have READ_PHONE_STATE Permission ", doesHavePermission);
      if (doesHavePermission) {
    	  this.setPhoneNumberAsUserName();
          //Initiate OTP Request
      } else {
          let reqid: number = Math.floor(Math.random() * 999);
          if (application.android.foregroundActivity) {
              console.log("Request Permission for Read Phone State using Foreground activity");
              android.support.v4.app.ActivityCompat.requestPermissions(application.android.foregroundActivity, [android.Manifest.permission.READ_PHONE_STATE], reqid);
          } else if (application.android.startActivity) {
              console.log("Request Permission for Read Phone State using startActivity");
              android.support.v4.app.ActivityCompat.requestPermissions(application.android.startActivity, [android.Manifest.permission.READ_PHONE_STATE], reqid);
          } else {
              
          }
      }
  }
  setPhoneNumberAsUserName() {
      let manager = application.android.context.getSystemService(android.content.Context.TELEPHONY_SERVICE);
      this.username = manager.getLine1Number();
  }
  pageLoaded( args ) {
      var page = args.object;
      page.actionBarHidden = true;
  }
  setPreferredLanguage(languageCode: string) {
      // this language will be used as a fallback when a translation isn't found in the current language
      this.translate.setDefaultLang('en');
       // the lang to use, if the lang isn't available, it will use the current loader to get them
      this.translate.use(languageCode);
  }
  doLogin() {
      console.log("doLogin!");
      let loginRequest: LoginRequest = new LoginRequest();
      loginRequest.userNameOrMobileNumber = this.username;
      loginRequest.password = "avatar";
      this.loginProgress = true;
      this.loginError = false;
      this.userService.login(loginRequest).subscribe(( loginResponse: LoginResponse ) => {
          this.loginResponse = loginResponse;
          this.loginProgress = false;
          this.setPreferredLanguage(this.loginResponse.userDTO.preferredLanguage['languageCode']);
          this.authService.setToken(this.loginResponse.accessToken);
          this.authService.setUserInfo(this.loginResponse.userDTO);
          console.info("Login Navigate to Home");
          this.router.navigate(["/home"]).then( (e) => {
              if (e) {
                  console.log("Navigation to Home is successful!");
                } else {
                  console.log("Navigation to Home has failed!");
                }
              });
      },
          ( error ) => {
              this.loginError = true;
              this.loginProgress = false;
              console.error("userService.login error!");
              console.error(error);
          });
  }

  }
