import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/authorization/userservice.generated';
import { UserDTO } from "../../services/authorization/userdto.model";
import { LoginRequest } from "../../services/authorization/loginrequest.model";
import { LoginResponse } from "../../services/authorization/loginresponse.model";
import { AuthService } from "../../services/auth.service";
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    username: string;
    password: string;
    loginResponse: LoginResponse;
    loginProgress: boolean;
    loginError: boolean;
  constructor(private userService: UserService, private authService: AuthService, 
          private translate: TranslateService, private router: Router) { }

  ngOnInit() {
      this.username = 'admin';
      this.password = 'admin';
      if (this.authService.isLogged()) {
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
      loginRequest.password = this.password;
      this.loginProgress = true;
      this.loginError = false;
      this.userService.login(loginRequest).subscribe(( loginResponse: LoginResponse ) => {
          this.loginResponse = loginResponse;
          this.loginProgress = false;
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
