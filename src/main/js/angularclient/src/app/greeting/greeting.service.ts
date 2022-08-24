import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GreetingModel} from "../model/greeting.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GreetingService {

  constructor(private http: HttpClient) { }

  getGreetingMessage(): Observable<GreetingModel> {

    var url = `${environment.testApiUrl}/message`;

    const httpOptions = {
      headers: {},
      params:  {}
    };
    return this.http.get<GreetingModel>(url, httpOptions);

  }


}
