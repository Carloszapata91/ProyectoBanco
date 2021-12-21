import { Component, OnInit } from '@angular/core';
import{ServiceMainService} from '../../shared/services/service-main.service'

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})

export class CategoryComponent implements OnInit {
  public bodyArray:Array<object>=[
    {
        "id": 3,
        "nombre": "Tennis adidas",
        "precio": 250000.0,
        "imagen": ""
    }
  ] 
  constructor(public service:ServiceMainService) { }

  ngOnInit(): void {
    this.service.getProducts().subscribe(res=>this.bodyArray=res["body"])

  }

  addItem(item){
    console.log(item)
  }

}
