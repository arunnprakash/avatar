import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'chips',
  templateUrl: './chips.component.html',
  styleUrls: ['./chips.component.css']
})
export class ChipsComponent implements OnInit {

  @Input() 
  dataItems: any[];
  @Input()
  field: string;

  constructor() { }

  ngOnInit() {
  }
  getText(item: any) {
      let text: string = '';
      let splittedFileds: string[] = this.field.split('.');
      splittedFileds.forEach( (splittedFiled: any ) => {
          item = item?item[splittedFiled]:null;
      });
      return item;
  }
}
