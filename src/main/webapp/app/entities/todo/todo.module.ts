import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HazelcasttestSharedModule } from 'app/shared/shared.module';
import { TodoComponent } from './todo.component';
import { TodoDetailComponent } from './todo-detail.component';
import { TodoUpdateComponent } from './todo-update.component';
import { TodoDeleteDialogComponent } from './todo-delete-dialog.component';
import { todoRoute } from './todo.route';

@NgModule({
  imports: [HazelcasttestSharedModule, RouterModule.forChild(todoRoute)],
  declarations: [TodoComponent, TodoDetailComponent, TodoUpdateComponent, TodoDeleteDialogComponent],
  entryComponents: [TodoDeleteDialogComponent]
})
export class HazelcasttestTodoModule {}
