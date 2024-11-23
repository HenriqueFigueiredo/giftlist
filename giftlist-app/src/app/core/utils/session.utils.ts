export function isLoggedUser(): boolean {
  if (typeof sessionStorage !== 'undefined') {
    const token = sessionStorage.getItem('authorization_token');
    const user = sessionStorage.getItem('logged_user');
    if (token && user) {
      return true;
    }
  }
  return false;
}

export function logoff() {
  if (typeof sessionStorage !== 'undefined') {
    sessionStorage.removeItem('authorization_token');
    sessionStorage.removeItem('logged_user');
    window.location.href = '/';
  }
}
