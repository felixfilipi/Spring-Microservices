import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor(@Inject(String) private url: string, private http: HttpClient) {}

  private onError = (error: any) => {
    if (error.status == 404) {
      alert('resource not found');
      return throwError(() => new Error('NOT FOUND'));
    } else {
      return error;
    }
  };

  getAll() {
    return this.http.get(this.url).pipe(catchError(this.onError));
  }

  create(resource: any) {
    return this.http
      .post(this.url, JSON.stringify(resource))
      .pipe(catchError(this.onError));
  }

  update(id: string) {
    return this.http
      .patch(this.url + '/' + id, { title: 'felix' })
      .pipe(catchError(this.onError));
  }

  delete(id: string) {
    return this.http.delete(this.url + '/' + id).pipe(catchError(this.onError));
  }
}
