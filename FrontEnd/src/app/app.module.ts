import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

//Componentes 
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import {AuthInterceptorService} from './shared/services/auth-interceptor.service';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { RegistroComponent } from './screens/registro/registro.component';
import { ContactoComponent } from './screens/contacto/contacto.component';
import { OperacionesComponent } from './operaciones/operaciones.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UsuarioComponent,
    UsuariosComponent,
    RegistroComponent,
    ContactoComponent,
    OperacionesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptorService,
      multi:true
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
