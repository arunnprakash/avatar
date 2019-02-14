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
      console.log("ngOnInit ChipsComponent");
  }

}
