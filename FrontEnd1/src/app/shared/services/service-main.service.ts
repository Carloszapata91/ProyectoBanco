import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment'
import { Observable } from 'rxjs';
import {Product} from '../models/products.model'

@Injectable({
  providedIn: 'root'
})
export class ServiceMainService {

  public url:string=environment.ApiUrl;

  constructor(public http:HttpClient) { }

  
getProducts(): Observable<Product>{
  return this.http.get<Product>(this.url)

}


}
