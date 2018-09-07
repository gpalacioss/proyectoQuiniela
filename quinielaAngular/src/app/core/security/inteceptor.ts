import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, 
    HttpResponse, HttpUserEvent, HttpErrorResponse } from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { TokenStorage } from './token.storage';
import 'rxjs/add/operator/do';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Inteceptor implements HttpInterceptor{

   constructor(private token: TokenStorage, private router: Router){}

   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>>{

    let authRequest = request;

    if(this.token.getToken() != null){
        authRequest = request.clone({headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }

    return next.handle(authRequest).do(
        (error: any) => {
            if(error instanceof HttpErrorResponse){
                console.log("error ::" + error);
                console.log("request url :: " + request.url)
                
                if(error.status == 401){
                    this.router.navigate(['appComponent'])
                }
            }
        }
    );
   }

}
