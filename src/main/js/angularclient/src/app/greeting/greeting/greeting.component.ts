import { Component, OnInit } from '@angular/core';
import { GreetingService } from "../greeting.service";

@Component({
  selector: 'app-greeting',
  templateUrl: './greeting.component.html',
  styleUrls: ['./greeting.component.css']
})
export class GreetingComponent implements OnInit {

  message: string | undefined = "";

  constructor(private _greetingService: GreetingService) { }

  ngOnInit(): void {

    this._greetingService.getGreetingMessage().subscribe({
        error: (error) => {
          console.log("Error happened! " + error);
        },    // errorHandler
        next: (data) => {
          this.message = data.message;
        },
      }
    );

  }

}
