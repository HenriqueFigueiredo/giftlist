import { Routes } from '@angular/router';
import { MainComponent } from './features/landingpage/pages/main/main.component';
import { LoginComponent } from './features/onboard/pages/login/login.component';
import { SignUpComponent } from './features/onboard/pages/sign-up/sign-up.component';
import { HomeComponent } from './features/giftlist/pages/home/home.component';
import { GiftListManagementComponent } from './features/giftlist/pages/gift-list-management/gift-list-management.component';
import { GiftFormComponent } from './features/giftlist/pages/gift-form/gift-form.component';
import { GiftListFormComponent } from './features/giftlist/pages/gift-list-form/gift-list-form.component';
import { CodeValidationComponent } from './features/onboard/pages/code-validation/code-validation.component';
import { GuestGiftListComponent } from './features/guest/pages/guest-gift-list/guest-gift-list.component';
import { GiftDetailsComponent } from './features/giftlist/pages/gift-details/gift-details.component';
import { TermsUseComponent } from './features/landingpage/pages/terms-use/terms-use.component';
import { PrivacyPolicyComponent } from './features/landingpage/pages/privacy-policy/privacy-policy.component';

export const ROUTES = {
  MAIN: '',
  LOGIN: 'login',
  SIGN_UP: 'sign-up',
  CODE_VALIDATION: 'code-validation',
  HOME: 'home',
  GIFT_LIST_FORM: 'gift-list-form',
  GIFT_LIST_MANAGEMENT: 'gift-list-management/:listId',
  GIFT_FORM: 'gift-form/:listId',
  GUEST_GIFT_LIST: 'guest-gift-list/:listId',
  GIFT_DETAILS: 'gift-details',
  TERMS_USE: 'terms-use',
  PRIVACY_POLICY: 'privacy-policy',
};

export const routes: Routes = [
  { path: ROUTES.MAIN, component: MainComponent },
  { path: ROUTES.LOGIN, component: LoginComponent },
  { path: ROUTES.SIGN_UP, component: SignUpComponent },
  { path: ROUTES.CODE_VALIDATION, component: CodeValidationComponent },
  { path: ROUTES.HOME, component: HomeComponent },
  { path: ROUTES.GIFT_LIST_FORM, component: GiftListFormComponent },
  { path: ROUTES.GIFT_LIST_MANAGEMENT, component: GiftListManagementComponent },
  { path: ROUTES.GIFT_FORM, component: GiftFormComponent },
  { path: ROUTES.GUEST_GIFT_LIST, component: GuestGiftListComponent },
  { path: ROUTES.GIFT_DETAILS, component: GiftDetailsComponent },
  { path: ROUTES.TERMS_USE, component: TermsUseComponent },
  { path: ROUTES.PRIVACY_POLICY, component: PrivacyPolicyComponent },
];
