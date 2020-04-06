import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { HazelcasttestSharedModule } from 'app/shared/shared.module';
import { HazelcasttestCoreModule } from 'app/core/core.module';
import { HazelcasttestAppRoutingModule } from './app-routing.module';
import { HazelcasttestHomeModule } from './home/home.module';
import { HazelcasttestEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    HazelcasttestSharedModule,
    HazelcasttestCoreModule,
    HazelcasttestHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    HazelcasttestEntityModule,
    HazelcasttestAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class HazelcasttestAppModule {}
