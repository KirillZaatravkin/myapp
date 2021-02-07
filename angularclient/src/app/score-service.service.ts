import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

import {HttpClient} from "@angular/common/http";
import {Score} from "./score";

@Injectable()
export class ScoreServiceService {

  private getScoreUrl: string;
  private updateScoreUrl: string;

  constructor(private http: HttpClient) {
    this.getScoreUrl = "http://localhost:8080/score";
    this.updateScoreUrl = "http://localhost:8080/scoreUpdate";
  }

  public findScore(): Observable<Score[]> {
    return this.http.get<Score[]>(this.getScoreUrl);
  }

  public updateScore(score: Score) {
    return this.http.post<Score>(this.updateScoreUrl, score);
  }

}
