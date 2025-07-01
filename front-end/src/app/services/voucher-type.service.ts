import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VoucherTypeService {
  private apiUrl = 'http://localhost:8080/farmacia/vouchertypes';

  constructor(private http: HttpClient) {}

  getAllVoucherTypes(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
