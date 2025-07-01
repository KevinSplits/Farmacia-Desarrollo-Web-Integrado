import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})
export class EmpleadosComponent implements OnInit {
  empleados: any[] = [];

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados(): void {
    this.employeeService.getAllEmployees().subscribe((data: any[]) => {
      this.empleados = data;
    });
  }

  anularEmpleado(id: number): void {
    if (confirm('¿Estás seguro de anular este empleado?')) {
      this.employeeService.deleteEmployee(id).subscribe(() => {
        this.cargarEmpleados();
      });
    }
  }
}
