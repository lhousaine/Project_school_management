import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  appTitle = 'ISMA';
  appSubTitle = 'ISMA system made to make school management easier.';
  currentSelectedRootTab = 0;
  previousRootTab = 0;
  indexClicked = 0;

  numItemsSelected = 0;
  breadcrumb1;
  breadcrumb2;
  breadcrumb3;
  rootTabs = [
    {
      id: 0,
      options: [
        {option: 'Acceuil', route: '/dashboard', icon: 'home'},
        {option: 'Étudiants', rootTab: 1, icon: 'face'},
        {option: 'Enseignants', rootTab: 2, icon: 'person'},
        {option: 'Cours', rootTab: 3, icon: 'import_contacts'},
        {option: 'Salles', rootTab: 4, icon: 'house'},
        {option: 'Planning', rootTab: 5, icon: 'calendar_today'},
        {option: 'Paiements', rootTab: 6, icon: 'attach_money'}
      ]
    },
    {
      id: 1,
      options: [
        {option: 'Ajouter un étudiant', route: '/dashboard'},
        {option: 'Chercher un étudiant', route: '/dashboard'}
      ]
    },
    {
      id: 2,
      options: [
        {option: 'Ajouter un prof', route: '/dashboard'},
        {option: 'Chercher un prof', route: '/dashboard'}
      ]
    },
    {
      id: 3,
      options: [
        {option: 'Ajouter un cour', route: '/dashboard'},
        {option: 'Chercher un cour', route: '/dashboard'},
      ]
    },
    {
      id: 4,
      options: [
        {option: 'Ajouter une salle', route: '/dashboard'},
        {option: 'Chercher une salle', route: '/dashboard'},
      ]
    },
    {
      id: 5,
      options: [
        {option: 'Créer un planning', route: '/dashboard'},
        {option: 'Générer un planning', route: '/dashboard'},
        {option: 'Chercher un planning', route: '/dashboard'},
      ]
    },
    {
      id: 6,
      options: [
        {option: 'Paiement Étudiant', rootTab: 7},
        {option: 'Paiement Prof', rootTab: 8}
      ]
    },
    {
      id: 7,
      options: [
        {option: 'Recever un paiement', route: '/dashboard'},
        {option: 'Rapport des paiements', route: '/dashboard'},
      ]
    },
    {
      id: 8,
      options: [
        {option: 'Payer un prof', route: '/dashboard'},
        {option: 'Rapport des paiements', route: '/dashboard'},
      ]
    },
  ];

  constructor(private auth: AuthenticationService,
              private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    this.auth.logOut();
    this.router.navigateByUrl('/login').then();
  }

  rootSelected(optionIndex, optionRootIndex, rootOption) {
    this.numItemsSelected++;
    this.previousRootTab = this.currentSelectedRootTab;
    this.currentSelectedRootTab = optionRootIndex;
    this.indexClicked = optionIndex;
    if (!this.breadcrumb1) {
      this.breadcrumb1 = rootOption;
    } else if (!this.breadcrumb2) {
      this.breadcrumb2 = rootOption;
    } else {
      this.breadcrumb3 = rootOption;
    }
  }

  back2Main() {
    this.currentSelectedRootTab = 0;
    this.previousRootTab = 0;
    this.indexClicked = 0;

    this.numItemsSelected = 0;
    this.breadcrumb1 = null;
    this.breadcrumb2 = null;
    this.breadcrumb3 = null;
  }

  toBreadcrum1() {
    if (this.numItemsSelected > 1) {
      this.currentSelectedRootTab = this.breadcrumb1.rootTab;
      this.breadcrumb2 = null;
      this.numItemsSelected--;
    }
  }

  toBreadcrum2() {
    if (this.numItemsSelected > 2) {
      this.currentSelectedRootTab = this.breadcrumb2.rootTab;
      this.breadcrumb3 = null;
      this.numItemsSelected--;
    }
  }
}
