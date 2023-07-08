import { Component, OnInit } from '@angular/core';
import { FormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../services/authentication.service';
import { Router } from '@angular/router';
import { LoginModel } from '../../../models/loginModel';
import { RegistrationModel } from '../../../models/registrationModel';
import { first } from 'rxjs';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    registrationForm: FormGroup;
    registrationModel: RegistrationModel = new RegistrationModel();

    constructor(private formBuilder: UntypedFormBuilder,
                private authService: AuthenticationService,
                private router: Router) {
    }

    ngOnInit() {
        this.registrationForm = this.formBuilder.group({
            userName: ['', Validators.required],
            email: ['', Validators.required],
            password: ['', Validators.required],
            repeatedPassowrd: ['', Validators.required]
        })
    }

    get formValues() {
        return this.registrationForm.controls;
    }

    registerNewUser() {
        console.log(this.formValues['repeatedPassowrd'].value)
        if (this.registrationForm.invalid) {
            console.error('invalid');
            return;
        }
        this.registrationModel.userName = this.formValues['userName'].value;
        this.registrationModel.email = this.formValues['email'].value;
        this.registrationModel.password = this.formValues['password'].value;
        this.authService.registerNewUser(this.registrationModel).pipe(first())
            .subscribe({
                next: () => {
                    console.info('success');
                    this.router.navigateByUrl('/dashboard');//this is dummy routing, user
                    // info and dashboard should be loaded

                },
                error: () => {
                    console.error('error')
                }
            });
    }
}
