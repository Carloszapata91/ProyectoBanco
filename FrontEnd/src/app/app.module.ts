import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import {AuthInterceptorService} from './shared/services/auth-interceptor.service';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { ProductosComponent } from './productos/productos.component';
import { ContactanosComponent } from './contactanos/contactanos.component';
import { NuestrosProductosComponent } from './nuestros-productos/nuestros-productos.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UsuarioComponent,
    UsuariosComponent,
    ProductosComponent,
    ContactanosComponent,
    NuestrosProductosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
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
