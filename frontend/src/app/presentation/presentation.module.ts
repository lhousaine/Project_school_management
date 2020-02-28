import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PresentationRoutingModule} from './presentation-routing.module';
import {AuthenticationModule} from './pages/authentication/authentication.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PresentationRoutingModule,
    AuthenticationModule
  ]
})
export class PresentationModule { }
