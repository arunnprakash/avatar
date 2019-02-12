import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';
import { Router, ActivatedRoute } from "@angular/router";

import { AuthService } from "../../../services/auth.service";
import { UserDTO } from "../../../services/authorization/userdto.model";

@Component({
  selector: 'seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent implements OnInit {

    constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      console.info( "On Init Seller Component tns" );
  }

}
