import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular/main';
import { GridOptions, RowNode } from 'ag-grid/main';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app a4';
  studentList = {};
  gridOptions = {};


   constructor(private http: HttpClient){
       this.gridOptions = <GridOptions>{
           context: {
               componentParent: this
           },
           columnDefs: this.createColumnDefs(),
           rowDefs: this.studentList,
       };
   }

   ngOnInit(): void {
     this.http.get('students/list').subscribe(data => {
       this.studentList = data;
       console.log(data);
       this.gridOptions.api.setRowData(this.studentList);
     });
   }

       private createColumnDefs() {
           return [
               {
                   headerName: "Name",
                   field: "name",
                   width: 400
               },
               {
                   headerName: "SSN",
                   field: "ssn",
                   width: 400,
               }
           ];
       }

}
