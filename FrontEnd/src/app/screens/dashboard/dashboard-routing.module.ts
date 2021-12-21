import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarProductsComponent } from '../car-products/car-products.component';
import { LoginComponent } from '../login/login.component';
import{ HomeComponent } from '../home/home.component';

import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
  { path: '', component: DashboardComponent, 
   children:[
     {path:'',redirectTo:'inicio',pathMatch:'full'},
     {path:'inicio',component:HomeComponent},
     {path:'login',component:LoginComponent}
   ]},
   {path:'card-product',component:CarProductsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
