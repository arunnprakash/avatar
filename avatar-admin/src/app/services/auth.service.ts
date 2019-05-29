import {Injectable} from '@angular/core';
import * as _ from "lodash";
import { UserDTO } from "../services/authorization/userdto.model";
import { RoleDTO } from "../services/authorization/roledto.model";
import { WareHouseDTO } from "../services/master/warehousedto.model";

const TOKEN = 'TOKEN';
const USER_INFO = 'USER_INFO';
const WARE_HOUSE_INFO = 'WARE_HOUSE_INFO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private localStorage: Storage) {}

  setWareHouse(wareHouse: WareHouseDTO): void {
      let jsonString: string = JSON.stringify(wareHouse);
      this.localStorage.setItem(WARE_HOUSE_INFO, jsonString);
  }
  getWareHouse(): WareHouseDTO {
      if (this.localStorage.getItem(WARE_HOUSE_INFO)) {
          let jsonString = this.localStorage.getItem(WARE_HOUSE_INFO);
          return JSON.parse(jsonString);
      } else {
          return null;
      }
  }
  setUserInfo(userInfo: UserDTO): void {
    let jsonString: string = JSON.stringify(userInfo);
    this.localStorage.setItem(USER_INFO, jsonString);
  }
  getUserInfo(): UserDTO {
      if (this.localStorage.getItem(USER_INFO)) {
          let jsonString = this.localStorage.getItem(USER_INFO);
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
      let userDTO: UserDTO = this.getUserInfo();
      let rolesDTO: RoleDTO[] = userDTO.roles;
      for (var i = 0; i < roles.length; i++) {
          let role: string = roles[i];
          for (var j = 0; j < rolesDTO.length; j++) {
              let roleDTO: RoleDTO = rolesDTO[j];
              if(roleDTO.roleName == role) {
                  return true;
              }
          }
      }
      return false;
  }
}