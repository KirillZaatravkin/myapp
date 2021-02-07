import {Component, OnInit} from '@angular/core';
import {Question} from "../question";
import {ActivatedRoute, Router} from "@angular/router";
import {QuestionServiceService} from "../question-service.service";
import {Observable} from "rxjs";
import {UploadServiceService} from "../upload-service.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";



@Component({
  selector: 'app-question-about',
  templateUrl: './question-about.component.html',
  styleUrls: ['./question-about.component.css'],


})
export class QuestionAboutComponent implements OnInit  {

  question: Question;
  id: string;

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(  private route: ActivatedRoute,
                private router: Router,
                private questionService: QuestionServiceService,
                private uploadService: UploadServiceService,
                private activatedRoute:  ActivatedRoute) {

  }

  updateQuestion() {
    this.questionService.add(this.question).subscribe(result => this.goto());
  }

  goto() {
    this.router.navigate(['/question']);
  }
  closeQuestion (){
    this.questionService.closeQuestion(this.question).subscribe(result => this.ngOnInit ());
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile, this.question.id).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();

    this.activatedRoute.queryParams.subscribe(
      data => this.id= data['id']);
    if (this.id != null){
      this.questionService.getQuestion(this.id).subscribe(data => {
        this.question = data;
      });
    }else {
      this.question = new Question();
    }
  }
}
