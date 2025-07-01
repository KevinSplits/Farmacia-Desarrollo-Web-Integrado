import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { LayoutService } from '../services/layout.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = { username: '', password: '' };
  error: string = '';
  loading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private layoutService: LayoutService
  ) {
    this.layoutService.hideSidebar();
  }

    onSubmit() {
  this.error = '';
  this.loading = true;
  
  this.authService.login(this.credentials).subscribe({
    next: (response) => {
      this.loading = false;
      this.router.navigate(['/']);
    },
    error: (err) => {
      this.loading = false;
      console.error('Error en login:', err);
      this.error = err.message || 'Error en la autenticación';
    }
  });
}
}