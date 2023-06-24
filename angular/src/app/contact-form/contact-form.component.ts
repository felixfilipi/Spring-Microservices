import { Component, ɵɵsetComponentScope } from '@angular/core';

@Component({
  selector: 'contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss'],
})
export class ContactFormComponent {
  isSubmitted: boolean = false;
  comment: string = '';
  contactMethods = [
    { id: 1, name: 'Email' },
    { id: 2, name: 'Phone' },
  ];

  log(x: Object) {
    console.log(x);
  }

  submit($event: any) {
    this.isSubmitted = !this.isSubmitted;

    $event.stopPropagation();
  }

  onDivClick() {
    console.log('div clicked');
  }

  onEnter() {
    // traditional way if textinput enterred
    console.log(this.comment);
  }

  submitForm(f: Object) {
    console.log(f);
  }
}
