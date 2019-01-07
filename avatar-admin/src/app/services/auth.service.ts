import {Injectable} from '@angular/core';
import * as _ from "lodash";
import { UserDTO } from "../services/authorization";

const TOKEN = 'TOKEN';
const USER_INFO = 'USER_INFO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private localStorage: Storage) {}

  setUserInfo(userInfo: UserDTO): void {
    let jsonString: string = JSON.stringify(userInfo);
    this.localStorage.setItem(USER_INFO, jsonString);
  }
  getUserInfo(): UserDTO {
      if (this.localStorage.getItem(USER_INFO)) {
          let jsonString = this.localStorage.getItem(USER_INFO);
          console.log(jsonString);
          return JSON.parse(jsonString);
      } else {
          return null;
      }
  }
  setToken(token: string): void {
    this.localStorage.setItem(TOKEN, token);
  }
  getToken() {
      return this.localStorage.getItem(TOKEN);
  }
  isLogged() {
    return this.localStorage.getItem(TOKEN) != null;
  }
  clearAll() {
      this.localStorage.clear();
  }
  hasRole(roles: string[]): boolean {
      console.log(this.getUserInfo().roles);
      return true;
  }
}