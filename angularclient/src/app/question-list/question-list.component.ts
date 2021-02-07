import { Component, OnInit } from '@angular/core';
import {Question} from "../question";
import {QuestionServiceService} from "../question-service.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.css']
})
export class QuestionListComponent implements OnInit {

  question : Question[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private questionService : QuestionServiceService) { }

  ngOnInit() {
    this.questionService.findAll().subscribe(data => {
      this.question = data;
    });
  }

}
