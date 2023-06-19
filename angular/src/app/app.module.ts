import { ErrorHandler, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { SummaryPipe } from './pipes/summary.pipe';
import { SignupFormComponent } from './signup-form/signup-form.component';
import { NewCourseFormComponent } from './new-course-form/new-course-form.component';
import { PostsComponent } from './posts/posts.component';
import { HttpClientModule } from '@angular/common/http'
import { PostsService } from './posts/services/posts.service';
import { AppErrorHandler } from './error/app-error-handler';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { GithubFollowersComponent } from './github-followers/github-followers.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    ContactFormComponent,
    SummaryPipe,
    SignupFormComponent,
    NewCourseFormComponent,
    PostsComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'followers/:id', component: GithubFollowersComponent},
      { path: 'posts', component: PostsComponent},
      { path: 'contact', component: ContactFormComponent},
      { path: 'new-course', component: NewCourseFormComponent},
      { path: 'signup', component: SignupFormComponent},
      { path: '**', component: NotFoundComponent},
    ])
  ],
  providers: [PostsService, {
    // processes all errors
    provide: ErrorHandler,
    useClass: AppErrorHandler,
  },
],
  bootstrap: [AppComponent]
})
export class AppModule { }
