import { Component, OnInit } from '@angular/core';
import { PostsService } from './services/posts.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnInit {
  posts: any;

  constructor(private service: PostsService) {}

  ngOnInit(): void {
    this.service.getAll().subscribe({
      next: (response) => {
        this.posts = response;
      },

      // INSTEAD DECLARING HERE, PUT ERROR LOGIC IN SERVICE
      // error: (error: Response) => {
      //   if (error.status === 404) {
      //     alert('This post has already been deleted.');
      //   } else {
      //     alert('an unexpected error occured.');
      //     console.log(error);
      //   }
      // },
    });
  }

  createPost(inp: HTMLInputElement) {
    let post: any = { title: inp.value };
    
    this.posts.splice(0, 0, post);
    
    inp.value = '';

    this.service.create(post).subscribe({
      next: (response : any) => {
        post['id'] = response;
        // this is pessimistic way, that will make application have delay, instead do this first, and check
        //  the api result later is new approach called optimistic way, which mean when something wrong happen
        // in the API we will rollback, and the rollback happen when error happened

        // this.posts.splice(0, 0, post);
      },
      error: (e : any) => {
        this.posts.splice(0,1);
      }
    });
  }

  updatePost(post: any) {
    // this.http.put(this.url, JSON.stringify(post));

    this.service.update(post.id).subscribe({
      next: (response: any) => {
        post['title'] = response.title;
      },
    });
  }

  deletePost(post: any) {
    let index = this.posts.indexOf(post);
    this.posts.splice(index, 1);

    this.service.delete(post.id).subscribe({
      next: (response) => {
        null
      },
      error: (e : any) => {
        this.posts.splice(index, 0, 1);
      }
    });
  }
}
