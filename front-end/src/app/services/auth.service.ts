import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap, catchError} from 'rxjs';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api'; // Ajusta la URL
  private tokenKey = 'auth_token';

  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: {username: string, password: string}): Observable<any> {
  return this.http.post(`${this.apiUrl}/auth/login`, credentials, {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }).pipe(
    tap((response: any) => {
      console.log('Respuesta del servidor:', response);
      localStorage.setItem(this.tokenKey, response.token);
      localStorage.setItem('user', JSON.stringify({
        username: response.username,
        roles: response.roles
      }));
    }),
    catchError(error => {
      console.error('Error completo:', error);
      if (error.error && error.error.message) {
        return throwError(() => new Error(error.error.message));
      }
      return throwError(() => new Error('Error de conexión con el servidor'));
    })
  );
}

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }

  getCurrentUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }
}