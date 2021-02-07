import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Score} from "./score";
import {ScoreServiceService} from "./score-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit   {

  score : Score [];
  title = 'Судья продажный';
  constructor(private route: ActivatedRoute,
              private router: Router,
              private scoreServiceService : ScoreServiceService) { }

  ngOnInit() {
    this.scoreServiceService.findScore().subscribe(data => {
      this.score = data;
    });
  }

  updatescore(score : Score) {
    this.scoreServiceService.updateScore(score).subscribe();
  }
}
