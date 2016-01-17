import {Component} from 'angular2/core';
import {CollectionListComponent} from '../collections/collection-list.component';
import {Router,ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'main-menu',
    templateUrl: 'app/menu/main-menu.html',
    directives: [ROUTER_DIRECTIVES]
})
export class MainMenuComponent { }