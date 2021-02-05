import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Vopros} from "./vopros";

@Injectable()
export class VoprosServiceService {
  private allProductUrl: string;
  private addProductUrl: string;
  private getProductUrl: string;
  private closeroductUrl: string;


  constructor(private http: HttpClient) {
    this.allProductUrl = 'http://localhost:8080/vopros';
    this.addProductUrl = 'http://localhost:8080/voprosadd';
    this.getProductUrl = 'http://localhost:8080/voprosaboutimg?id=';
    this.closeroductUrl = 'http://localhost:8080/voprosclose';

  }
  public findAll(): Observable<Vopros[]> {
    return this.http.get<Vopros[]>(this.allProductUrl);
  }

  public add(prod: Vopros) {
    return this.http.post<Vopros>(this.addProductUrl, prod);
  }

  public getProduct(id: string): Observable<Vopros>{
    return this.http.get<Vopros>(this.getProductUrl+id);
  }

  public closePRoduct(prod: Vopros) {
    return this.http.post<Vopros>(this.closeroductUrl, prod);
  }

}
