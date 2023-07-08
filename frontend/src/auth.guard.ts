import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { AuthenticationService } from './app/services/authentication.service';
import { userModel } from './app/models/userModel';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private accountService: AuthenticationService
    ) { }

    canActivate() {
        let user!:userModel | undefined
        user = this.accountService.getCurrentUser
        console.table(user?.userName)
        if (user?.userName != undefined) {
            // authorised so return true
            console.log("authorised access!")
            return true;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigateByUrl("/login")
        console.log("no authorised access!")
        return false;
    }
}