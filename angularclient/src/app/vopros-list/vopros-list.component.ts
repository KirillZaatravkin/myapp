import { Component, OnInit } from '@angular/core';
import {Vopros} from "../vopros";
import {VoprosServiceService} from "../vopros-service.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-vopros-list',
  templateUrl: './vopros-list.component.html',
  styleUrls: ['./vopros-list.component.css']
})
export class VoprosListComponent implements OnInit {

  vopros : Vopros[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private  voprosServiceService : VoprosServiceService) { }

  ngOnInit() {
    this.voprosServiceService.findAll().subscribe(data => {
      this.vopros = data;
    });
  }

}
