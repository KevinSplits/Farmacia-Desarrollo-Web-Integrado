import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../services/employee.service';
import { Employee } from '../models/employee.model';

@Component({
  selector: 'app-editar-empleado',
  templateUrl: './editar-empleado.component.html',
  styleUrls: ['./editar-empleado.component.css']
})
export class EditarEmpleadoComponent implements OnInit {
  empleado: Employee = {
    idempleado: 0,
    nombres: '',
    apellidos: '',
    dni: '',
    telefono: '',
    email: '',
    direccion: '',
    sexo: '',
    estado: 'Activo',
    idusuario: 0
  };
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.employeeService.getEmployeeById(id).subscribe({
        next: (data) => {
          this.empleado = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'No se pudo cargar el empleado';
          this.loading = false;
        }
      });
    } else {
      this.error = 'ID de empleado inválido';
      this.loading = false;
    }
  }

  guardar() {
    this.employeeService.updateEmployee(this.empleado.idempleado, this.empleado).subscribe({
      next: () => this.router.navigate(['/empleados']),
      error: () => this.error = 'No se pudo actualizar el empleado'
    });
  }
}
