import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './screens/home/home.component';

const routes: Routes = [
  { path:'', redirectTo:'dashboard', pathMatch:'full' },
  { path: 'dashboard', loadChildren: () => import('./screens/dashboard/dashboard.module').then(m => m.DashboardModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
