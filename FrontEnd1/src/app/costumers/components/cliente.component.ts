import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usuario',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class UsuarioComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Modelo/Client';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  

  constructor(private http:HttpClient) { }
  Url='http://localhost:3030/clients01';

  getClients(){
    return this.http.get<Client[]>(this.Url)
  }
  createClient(client:Client){
    return this.http.post<Client>(this.Url,client)
  }
  getClientId(id_clients:number){
    return this.http.get<Client>(this.Url+"/"+id_clients)
  }
  updateClient(client:Client){
    return this.http.put<Client>(this.Url+"/"+client.id_clients,client);
  }
  deleteClient(client:Client){
    return this.http.delete<Client>(this.Url+"/eliminar/"+client.id_clients);
  }
}