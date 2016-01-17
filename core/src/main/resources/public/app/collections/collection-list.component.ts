import {Component} from 'angular2/core';
import {Router,ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'collection-list',
    template: `
    	<ul>
			<li>Collection 1 <a [routerLink]="['CollectionView']">View</a> <a href="#">X</a></li>
		</ul>
    `,
	directives: [ROUTER_DIRECTIVES]
})
export class CollectionListComponent { }