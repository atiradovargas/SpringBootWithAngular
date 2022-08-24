import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GreetingComponent } from './greeting/greeting.component';
import {RouterModule} from "@angular/router";

const routes = [
  {
    path     : 'welcome',
    component: GreetingComponent
  }
];

@NgModule({
  declarations: [
    GreetingComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ],
  exports: [
    GreetingComponent
  ]
})
export class GreetingModule { }
