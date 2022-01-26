import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './screens/home/home.component';
import {ReactiveFormsModule} from '@angular/forms'
import { ContactoComponent } from './screens/contacto/contacto.component';
import { UsuarioComponent } from './usuario/usuario.component';

const routes: Routes = [
  { path:'', redirectTo:'dashboard', pathMatch:'full' },
  { path: 'dashboard', loadChildren: () => import('./screens/dashboard/dashboard.module').then(m => m.DashboardModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),FormsModule,ReactiveFormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }


