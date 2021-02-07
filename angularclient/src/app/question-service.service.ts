import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "./question";

@Injectable()
export class QuestionServiceService {
  private allQuestionUrl: string;
  private addQuestionUrl: string;
  private getQuestionUrl: string;
  private closeQuestionUrl: string;


  constructor(private http: HttpClient) {
    this.allQuestionUrl = 'http://localhost:8080/question';
    this.addQuestionUrl = 'http://localhost:8080/questionadd';
    this.getQuestionUrl = 'http://localhost:8080/questionaboutimg?id=';
    this.closeQuestionUrl = 'http://localhost:8080/questionclose';

  }
  public findAll(): Observable<Question[]> {
    return this.http.get<Question[]>(this.allQuestionUrl);
  }

  public add(prod: Question) {
    return this.http.post<Question>(this.addQuestionUrl, prod);
  }

  public getQuestion(id: string): Observable<Question>{
    return this.http.get<Question>(this.getQuestionUrl+id);
  }

  public closeQuestion(prod: Question) {
    return this.http.post<Question>(this.closeQuestionUrl, prod);
  }

}
