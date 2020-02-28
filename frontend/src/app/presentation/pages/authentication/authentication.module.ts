import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginPageComponent} from './login-page/login-page.component';
import {MaterialsModule} from '../../materials/materials.module';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [LoginPageComponent],
  imports: [
    CommonModule,
    MaterialsModule,
    ReactiveFormsModule,
    RouterModule,
  ]
})
export class AuthenticationModule {
}
