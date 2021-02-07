import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { QuestionListComponent } from './question-list/question-list.component';
import {QuestionServiceService} from "./question-service.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { QuestionAboutComponent } from './question-about/question-about.component';
import { FileUploadModule} from "ng2-file-upload";
import {UploadServiceService} from "./upload-service.service";
import {ScoreServiceService} from "./score-service.service";

const routes: Routes = [
  { path: 'question', component: QuestionListComponent },
  { path: 'questionabout', component: QuestionAboutComponent },

];

@NgModule({
  declarations: [
    AppComponent,
    QuestionListComponent,
    QuestionAboutComponent,

  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(routes),
        RouterModule,
        FileUploadModule
    ],
  providers: [QuestionServiceService,  UploadServiceService, ScoreServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
