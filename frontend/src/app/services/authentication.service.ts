import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  PROXY_PORT = 8888;
  host = 'http://localhost:' + this.PROXY_PORT;
  TOKEN_KEY = 'school-token';

  roles: Array<string>;
  username: string;
  jwt: string;

  constructor(private http: HttpClient) {
  }

  public initCredentials() {
    this.jwt = undefined;
    this.username = undefined;
    this.roles = undefined;
  }

  public authenticate(data) {
    return this.http.post(this.host + '/USERS-SERVICE/login',
      data, {observe: 'response'});
  }

  public parseJwt() {
    const jwtHelper = new JwtHelperService();
    const objJwt = jwtHelper.decodeToken(this.jwt);
    this.username = objJwt.obj;
    this.roles = objJwt.roles;
  }

  /*
    Authentication utils
   */
  public isAdmin() {
    return this.roles.indexOf('ADMIN') >= 0;
  }

  public isAgent() {
    return this.roles.indexOf('AGENT') >= 0;
  }

  public isClient() {
    return this.roles.indexOf('CLIENT') >= 0;
  }

  public isAuthenticated() {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  public logOut() {
    localStorage.removeItem(this.TOKEN_KEY);
    this.initCredentials();
  }

  /*
    Token utils
   */
  public saveToken(jwt: string) {
    localStorage.setItem(this.TOKEN_KEY, jwt);
    this.jwt = jwt;
    this.parseJwt();
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  public loadToken() {
    if (!(this.username === undefined && this.roles === undefined)) {
      this.jwt = localStorage.getItem(this.TOKEN_KEY);
      this.parseJwt();
    }
  }
}
