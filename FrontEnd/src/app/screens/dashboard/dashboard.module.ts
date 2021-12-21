import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from '../home/home.component';
import { CategoryComponent } from '../login/category.component';
import { CarProductsComponent } from '../car-products/car-products.component';


@NgModule({
  declarations: [
    DashboardComponent,
    HomeComponent,
    CategoryComponent,
    CarProductsComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
