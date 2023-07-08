import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';
import { FormBuilder, FormGroup, UntypedFormBuilder, UntypedFormControl, Validators } from '@angular/forms';
import { LoginModel } from '../../../models/loginModel';
import { first } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    loginForm!: FormGroup;
    loginModel: LoginModel = new LoginModel();

    constructor(private formBuilder: UntypedFormBuilder,
                private authService: AuthenticationService,
                private router:Router) {
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    get formValues() {
        return this.loginForm.controls;
    }

    onLogin() {
        if (this.loginForm.invalid) {
            console.error()
            return;
        }
        this.loginModel.userName = this.formValues['username'].value;
        this.loginModel.password = this.formValues['password'].value;
        this.authService.loginUser(this.loginModel)
            .subscribe(loginResponse => {
                    console.info('success');
                    this.router.navigateByUrl('/dashboard');//this is dummy routing, user
                    // info and dashboard should be loaded
                    this.authService.setCurrentUser(this.loginModel.userName,loginResponse.accessToken);
            });
    }
}
