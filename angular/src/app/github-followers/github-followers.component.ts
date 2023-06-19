import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest, map, switchMap } from 'rxjs';

@Component({
  selector: 'app-github-followers',
  templateUrl: './github-followers.component.html',
  styleUrls: ['./github-followers.component.scss'],
})
export class GithubFollowersComponent {
  constructor(private _route: ActivatedRoute, private router: Router) {}

  id: string | null = '';
  // Use this if we sure user will leave the page and need to recreate it.
  // id = this._route.snapshot.paramMap.get('id')

  // the better approach, instead get the param again, we can store it once using onInit and
  // store it on observable (reduce load task)
  ngOnInit() {
    // in most cases we need subscribe for queryparammap because we will need to back to the page
    // like in pagination

    //  ----------------------------------------------------------------------
    // instead of create 2 observable call, we can combine both
    // and subscribe to the combined observable
    //  ----------------------------------------------------------------------
    this._route.paramMap.subscribe(params => {
      this.id = params.get('id');
    })

    // this._route.queryParamMap.subscribe(params => {
    // })
    //  ----------------------------------------------------------------------
    // let obs = combineLatest([this._route.paramMap, this._route.queryParamMap])
    //   .pipe(
    //     switchMap(combined => {
    //       let id = combined[0].get('id');
    //       let page = combined[1].get('page');

    //       // get data from service based on this parameter
    //       // this.service.getAll.subscribe({ id: id, page: page })
          
          
    //       // return this.service.getAll()
    //     })
    //   )
      // .subscribe((followers) => {this.followers = followers});
  }

  // on submit we will navigate to followes with params bla bla
  submit(){
    this.router.navigate(['/followers'], {
      queryParams: {page: 1, order: 'newest'}
    })
  }
}
