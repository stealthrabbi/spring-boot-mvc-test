import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app a4';
  studentList = {};

   constructor(private http: HttpClient){
   }

   ngOnInit(): void {
     this.http.get('students/list').subscribe(data => {
       this.studentList = data;
       console.log(data);
     });
   }
}
