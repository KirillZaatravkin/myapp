import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

import {HttpClient} from "@angular/common/http";
import {Cset} from "./cset";
import {Vopros} from "./vopros";

@Injectable()
export class CsetServiceService {

  private getCsetUrl: string;
  private updateCsetUrl: string;

  constructor(private http: HttpClient) {
    this.getCsetUrl = "http://localhost:8080/cset";
    this.updateCsetUrl = "http://localhost:8080/csetUpdate";
  }

  public findCset(): Observable<Cset[]> {
    return this.http.get<Cset[]>(this.getCsetUrl);
  }

  public updateCset(cset: Cset) {
    return this.http.post<Cset>(this.updateCsetUrl, cset);
  }

}
