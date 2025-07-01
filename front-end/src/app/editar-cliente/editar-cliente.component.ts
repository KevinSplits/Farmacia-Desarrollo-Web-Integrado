import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../services/client.service';
import { Client } from '../models/client.model';

@Component({
  selector: 'app-editar-cliente',
  templateUrl: './editar-cliente.component.html',
  styleUrls: ['./editar-cliente.component.css']
})
export class EditarClienteComponent implements OnInit {
  cliente: Client = {
    idcliente: 0,
    nombres: '',
    apellidos: '',
    dni: '',
    telefono: '',
    email: '',
    direccion: '',
    sexo: '',
    estado: 'Activo'
  };
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.clientService.getClientById(id).subscribe({
        next: (data) => {
          this.cliente = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'No se pudo cargar el cliente';
          this.loading = false;
        }
      });
    } else {
      this.error = 'ID de cliente inválido';
      this.loading = false;
    }
  }

  guardar() {
    this.clientService.updateClient(this.cliente.idcliente, this.cliente).subscribe({
      next: () => this.router.navigate(['/clientes']),
      error: () => this.error = 'No se pudo actualizar el cliente'
    });
  }
}
