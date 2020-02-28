import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../../services/authentication.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {SubSink} from 'subsink';

@Component({
  selector: 'app-login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  rememberMe: boolean;
  userLoginForm: FormGroup;
  backgroundImagePath: string;
  errorLogin: boolean;
  private subSink = new SubSink();

  constructor(private authService: AuthenticationService,
              private router: Router,
              private formBuilder: FormBuilder) {
    if (this.authService.isAuthenticated()) {
      this.router.navigateByUrl('/dashboard').then();
    }
  }

  ngOnInit() {
    this.backgroundImagePath = "assets/img/login-background.png";
    this.rememberMe = false;
    this.errorLogin = false;
    this.initLoginForm();
  }

  initLoginForm() {
    this.userLoginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      rememberMe: false,
    });
  }

  onSubmitForm() {
    const loginFormValue = this.userLoginForm.value;
    console.log(loginFormValue);
    const credentials = {username: loginFormValue.username, password: loginFormValue.password};
    this.subSink.add(this.authService.authenticate(credentials)
      .subscribe(response => {
        this.errorLogin = false;
        const jwt = response.headers.get('Authorization');
        this.authService.saveToken(jwt);
        this.router.navigateByUrl('/dashboard')
          .then();
      }, err => {
        this.errorLogin = true;
      }));
  }
}

