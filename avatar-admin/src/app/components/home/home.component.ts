import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
               trigger('animation', [
                   state('visible', style({
                       transform: 'translateX(0)',
                       opacity: 1
                   })),
                   transition('void => *', [
                       style({transform: 'translateX(50%)', opacity: 0}),
                       animate('300ms ease-out')
                   ]),
                   transition('* => void', [
                       animate(('250ms ease-in'), style({
                           height: 0,
                           opacity: 0,
                           transform: 'translateX(50%)'
                       }))
                   ])
               ])
           ],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
      console.info("Home Component");
  }

}
