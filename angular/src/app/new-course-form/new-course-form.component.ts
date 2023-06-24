import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'new-course-form',
  templateUrl: './new-course-form.component.html',
  styleUrls: ['./new-course-form.component.scss']
})
export class NewCourseFormComponent {

  // native way
  // form = new FormGroup({
  //     name: new FormControl(),
  //     contact: new FormGroup({
  //       email: new FormControl(),
  //       phone: new FormControl()
  //     }),
  //     topics: new FormArray([])
  // })

  form;
  // For cleaner way to define our form we can use form builder
  constructor(fb : FormBuilder){
    this.form = fb.group({
      name: ['', Validators.required],
      contact: fb.group({
        email: [],
        phone: [],
      }),
      topics: fb.array([])
    })
  }

  addTopic(topic : HTMLInputElement){
    this.topics.push(new FormControl(topic.value))
    topic.value='';
  }

  get topics(){
    return this.form.get('topics') as FormArray;
  }

  removeTopic(topic : FormControl){
    let index = this.topics.controls.indexOf(topic);
    this.topics.removeAt(index);
  }
}
