export interface ITodo {
  id?: number;
  description?: string;
  details?: string;
  done?: boolean;
}

export class Todo implements ITodo {
  constructor(public id?: number, public description?: string, public details?: string, public done?: boolean) {
    this.done = this.done || false;
  }
}
