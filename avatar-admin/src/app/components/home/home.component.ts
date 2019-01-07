import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';
import { Router, ActivatedRoute } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { UserDTO } from "../../services/authorization/userdto.model";

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

    userDTO: UserDTO;

    constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      console.info("Home Component");
      this.userDTO = this.authService.getUserInfo();
  }
  logout() {
      this.authService.clearAll();
      this.router.navigate(['/login'], { relativeTo: this.activatedRoute }).then( (e) => {
          if (e) {
              console.log("Navigation to login successful!");
            } else {
              console.log("Navigation to login has failed!");
            }
      });
  }
}
