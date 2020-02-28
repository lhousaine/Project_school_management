import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PagesRoutingModule} from './pages-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { HomePageComponent } from './home-page/home-page.component';
import {MaterialsModule} from '../materials/materials.module';
import {AuthenticationModule} from './authentication/authentication.module';

@NgModule({
  declarations: [LayoutComponent, HomePageComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
    MaterialsModule,
    AuthenticationModule,
  ]
})
export class PagesModule { }
