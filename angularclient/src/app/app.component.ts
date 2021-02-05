import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Cset} from "./cset";
import {CsetServiceService} from "./cset-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit   {

  cset : Cset [];
  title = 'Судья продажный';
  constructor(private route: ActivatedRoute,
              private router: Router,
              private  csetServiceService : CsetServiceService) { }

  ngOnInit() {
    this.csetServiceService.findCset().subscribe(data => {
      this.cset = data;
    });
  }
  updateCset(cset : Cset) {
    this.csetServiceService.updateCset(cset).subscribe();
  }
}
