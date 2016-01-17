import {Component} from 'angular2/core';
import {
		RouteConfig,
	    RouterLink,
	    RouterOutlet,
	    Route,
	    ROUTER_DIRECTIVES,
	    ROUTER_PROVIDERS,
	    Location,
	    LocationStrategy,
	    HashLocationStrategy,
	    Router
} from 'angular2/router';

import {MainMenuComponent} from './menu/main-menu.component';
import {CollectionListComponent} from './collections/collection-list.component';
import {CollectionViewComponent} from './collections/view/collection-view.component';


@Component({
    selector: 'my-app',
    templateUrl: 'app/app.html',
    directives: [MainMenuComponent, ROUTER_DIRECTIVES],
})
@RouteConfig([
  //{ path: '/', redirectTo: ['/Auth'] },
  { path:'/collections',   as: 'Collections',  component: CollectionListComponent },
  { path:'/collections/view',   as: 'CollectionView',  component: CollectionViewComponent },
])
export class AppComponent { }
