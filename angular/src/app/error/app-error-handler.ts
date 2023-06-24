import { ErrorHandler, Injectable, NgModule } from "@angular/core";

@Injectable()
export class AppErrorHandler implements ErrorHandler{
    handleError(error: any): void {
        alert('an unexpected error occured.');
        console.log(error);
    }
}