import { Component, OnInit } from '@angular/core';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

import { baseDetailTemplate } from '../../../base-detail/base.detail.template';
import { baseDetailCss } from '../../../base-detail/base.detail.css';
import { BaseDetailComponent } from '../../../base-detail/base.detail.component';
import { AuthService } from "../../../../services/auth.service";
import { VillageService } from "../../../../services/master/villageservice.generated";

@Component({
  selector: 'village-detail',
  template: baseDetailTemplate,
  styles: [baseDetailCss]
})
export class VillageDetailComponent extends BaseDetailComponent implements OnInit {

  constructor(villageService: VillageService, authService: AuthService, 
          params: DynamicDialogConfig, dialog: DynamicDialogRef ) {
      super(villageService, authService, params, dialog);
  }

  ngOnInit() {
      super.ngOnInit();
      console.log("ngOnInit Village Detail Component.ts");
  }
  protected isModelValid(): boolean {
      return true;
  }
}
