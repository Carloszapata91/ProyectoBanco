import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarProductsComponent } from './car-products.component';

describe('CarProductsComponent', () => {
  let component: CarProductsComponent;
  let fixture: ComponentFixture<CarProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
