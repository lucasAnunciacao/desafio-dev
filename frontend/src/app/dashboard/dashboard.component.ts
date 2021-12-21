import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { DashboardService } from './dashboard.service';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  cnabCompany: any;

  
  

  constructor(private service: DashboardService) {}
  
  ngOnInit() {
    const params = '';  
    this.service.getCnabsAgreggateByCompany(params)
        .subscribe((res) => {
          this.cnabCompany = res;
        }
    );
  
      
  }

}
