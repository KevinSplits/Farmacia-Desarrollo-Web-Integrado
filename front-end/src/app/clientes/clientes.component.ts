import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
})
export class ClientesComponent implements OnInit {
  clientes: any[] = [];
  constructor(private clientService: ClientService) {}

  ngOnInit() {
    this.cargarClientes();
  }

  cargarClientes() {
    this.clientService.getAllClients().subscribe((data: any[]) => {
      this.clientes = data;
    });
  }

  anularCliente(id: number) {
    if (confirm('¿Estás seguro de anular este cliente?')) {
      this.clientService.deleteClient(id).subscribe(() => {
        this.cargarClientes();
      });
    }
  }
}
