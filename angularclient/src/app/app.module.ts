import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { VoprosListComponent } from './vopros-list/vopros-list.component';
import {VoprosServiceService} from "./vopros-service.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { VoprosAboutComponent } from './vopros-about/vopros-about.component';
import { FileUploadModule} from "ng2-file-upload";
import {UploadServiceService} from "./upload-service.service";
import {CsetServiceService} from "./cset-service.service";

const routes: Routes = [
  { path: 'product', component: VoprosListComponent },
  { path: 'productabout', component: VoprosAboutComponent },

];

@NgModule({
  declarations: [
    AppComponent,
    VoprosListComponent,
    VoprosAboutComponent,

  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(routes),
        RouterModule,
        FileUploadModule
    ],
  providers: [VoprosServiceService,  UploadServiceService, CsetServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
