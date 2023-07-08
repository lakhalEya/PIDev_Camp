import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegistrationModel } from '../models/registrationModel';
import { BehaviorSubject, first, Observable } from 'rxjs';
import { userModel } from '../models/userModel';
import { LoginModel } from '../models/loginModel';
import { LoginResponse } from '../models/loginResponse';
import value from '*.json';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private baseUrl = 'http://127.0.0.1:8081/tunCamp'
    private currentUser: BehaviorSubject<userModel> = new BehaviorSubject<userModel>(new userModel());

    constructor(private http: HttpClient) {
    }

    registerNewUser(registerModel: RegistrationModel): Observable<userModel> {
        return this.http.post<userModel>(this.baseUrl + '/user/register', registerModel);
    }

    loginUser(loginModel: LoginModel): Observable<LoginResponse> {
         return this.http.post<LoginResponse>(this.baseUrl + '/user/login', loginModel);
    }

    getUser(username: string | undefined, authToken:string|undefined): Observable<userModel> {
        const headers = { 'Authorization': 'Bearer ' + authToken };
        // @ts-ignore
        return this.http.get<userModel>(this.baseUrl + '/user/getByName?userName=' + username,{headers});
    }

    setCurrentUser(userName: string | undefined,token:string|undefined) {
        this.getUser(userName,token).subscribe(value1 => {
            this.currentUser.next(value1)
        });
    }
    get getCurrentUser() {return this.currentUser.value}
}
