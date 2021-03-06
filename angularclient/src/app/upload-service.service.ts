import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class UploadServiceService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  upload(file: File, id : string): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}
