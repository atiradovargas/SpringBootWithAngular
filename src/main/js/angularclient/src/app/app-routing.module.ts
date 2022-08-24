import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {GreetingComponent} from "./greeting/greeting/greeting.component";


const routes: Routes = [
  {
    path      : '**',
    redirectTo: 'welcome'
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
