import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import{ServiceMainService} from '../../shared/services/service-main.service'

import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
login(form: NgForm)  {
  const usuario = form.value.usuario;
  const contrasena = form.value.contrasena

}
 

}
