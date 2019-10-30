import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SudokuObj } from './app.module';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title: string;
  url = 'http://localhost:8080/sudoku/board';
  data: String[];
  selectedRow: Number;
  selectedColumn: Number;
  selectedValue: CharacterData;
  obj: SudokuObj;



  constructor(private http: HttpClient, private spinnerService: Ng4LoadingSpinnerService) {
    this.title = 'Sudoku Application';
    
  }

  async LoadSudoku() {
    this.spinnerService.show();
    this.data = await this.serverCall(this.url);
    this.spinnerService.hide();
  }

  public onClick(selectedRow, selectedColumn, selectedValue, value) {
    this.selectedRow = selectedRow;
    this.selectedColumn = selectedColumn;
    this.selectedValue = selectedValue;
    value.toElement.bgColor='aqua'
    this.obj = new SudokuObj();
  }

  async ReloadSudoku() {
    this.spinnerService.show();
    this.data = null;
    if(this.selectedColumn == null || this.selectedColumn == undefined){
      this.data = await this.serverCall(this.url);
      this.spinnerService.hide();
      this.selectedColumn = null;
      this.selectedRow = null;
      this.selectedValue = null;
      return;
    }
    this.obj.column = this.selectedColumn;
    this.obj.row = this.selectedRow;
    this.obj.val = this.selectedValue;
    this.data = await this.serverCallForPost(this.url, this.obj);
    this.spinnerService.hide();
    this.selectedColumn = null;
    this.selectedRow = null;
    this.selectedValue = null;
  }

  public serverCall(url: string): Promise<any> {
    return this.http.get(url).toPromise();
  }

  public serverCallForPost(url: string, obj: SudokuObj): Promise<any> {
    const data = JSON.stringify(obj);
    return this.http.post(url, data, {
     headers: new HttpHeaders({'Content-Type': 'application/json', 'accept': 'application/json', 'Access-Control-Allow-Origin': '*'})
    }).toPromise();

  }
  // tslint:disable-next-line: use-life-cycle-interface
  ngOnInit() {
    this.LoadSudoku();
  }
}
