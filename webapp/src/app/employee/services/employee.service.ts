import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private EMPLOYEE_SERVICE_BASE_URL : string;
  private EMPLOYEE_ID : number;

  constructor() {
    this.EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees";
    this.EMPLOYEE_ID = 2;
   }

   getEmployee(){
     return axios.get(this.EMPLOYEE_SERVICE_BASE_URL + "/" + this.EMPLOYEE_ID);
   }
}
