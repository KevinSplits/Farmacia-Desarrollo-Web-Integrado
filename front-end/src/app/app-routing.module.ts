import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './clientes/clientes.component'; // importa tu componente
import { AgregarClienteComponent } from './agregar-cliente/agregar-cliente.component';
import { EmpleadosComponent } from './empleados/empleados.component';
import { AgregarEmpleadoComponent } from './agregar-empleado/agregar-empleado.component';
import { EditarEmpleadoComponent } from './editar-empleado/editar-empleado.component';

const routes: Routes = [
  { path: 'clientes', component: ClientesComponent }, 
  { path: 'clientes/agregar', component: AgregarClienteComponent } ,
  { path: 'clientes/editar', component: EditarEmpleadoComponent }
  , { path: 'empleados', component: EmpleadosComponent }
  , { path: 'empleados/agregar', component: AgregarEmpleadoComponent }
  , { path: 'empleados/editar', component: EditarEmpleadoComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
