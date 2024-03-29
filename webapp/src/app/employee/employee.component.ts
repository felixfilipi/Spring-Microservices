import { Component } from '@angular/core';
import { Department } from './classes/Department';
import { Employee } from './classes/Employee';
import { Organization } from './classes/Organization';
import { EmployeeService } from './services/employee.service';

@Component({
  selector: 'employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
})

export class EmployeeComponent {
  employee! : Employee;
  department! : Department;
  organization! : Organization;

  constructor(employeeService: EmployeeService){
    employeeService.getEmployee().then((response) => {
      this.employee = response.data.employee;
      this.department = response.data.department;
      this.organization = response.data.organization;
    });
    console.log(this.department);
    console.log(this.employee);
    console.log(this.organization);
  }
}
