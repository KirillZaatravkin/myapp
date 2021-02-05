import {Component, OnInit} from '@angular/core';
import {Vopros} from "../vopros";
import {ActivatedRoute, Router} from "@angular/router";
import {VoprosServiceService} from "../vopros-service.service";
import {Observable} from "rxjs";
import {UploadServiceService} from "../upload-service.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";



@Component({
  selector: 'app-vopros-about',
  templateUrl: './vopros-about.component.html',
  styleUrls: ['./vopros-about.component.css'],


})
export class VoprosAboutComponent implements OnInit  {

  vopros: Vopros;
  id: string;

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(  private route: ActivatedRoute,
                private router: Router,
                private productService: VoprosServiceService,
                private uploadService: UploadServiceService,
                private activatedRoute:  ActivatedRoute) {

  }

  updateProduct() {
    this.productService.add(this.vopros).subscribe(result => this.goto());
  }

  goto() {
    this.router.navigate(['/product']);
  }
  closeProduct (){
    this.productService.closePRoduct(this.vopros).subscribe(result => this.ngOnInit ());
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile, this.vopros.id).subscribe(
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
      this.productService.getProduct(this.id).subscribe(data => {
        this.vopros = data;
      });
    }else {
      this.vopros = new Vopros();
    }
  }
}
