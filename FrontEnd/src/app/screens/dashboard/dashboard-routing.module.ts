import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarProductsComponent } from '../car-products/car-products.component';
import { CategoryComponent } from '../login/category.component';
import{ HomeComponent } from '../home/home.component';

import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
  { path: '', component: DashboardComponent, 
   children:[
     {path:'',redirectTo:'inicio',pathMatch:'full'},
     {path:'inicio',component:HomeComponent},
     {path:'category',component:CategoryComponent}
   ]},
   {path:'card-product',component:CarProductsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
