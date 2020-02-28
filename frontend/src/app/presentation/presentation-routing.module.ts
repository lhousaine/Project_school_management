import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LayoutComponent} from './pages/layout/layout.component';
import {AuthGuardService} from '../services/auth-guard.service';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {LoginPageComponent} from './pages/authentication/login-page/login-page.component';

const routes: Routes = [
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PresentationRoutingModule {
}
