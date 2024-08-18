export function getUser() {
  const tenant = sessionStorage.getItem('tenant');
  return tenant ? JSON.parse(tenant) : null;
}

export function setUser(tenant) {
  sessionStorage.setItem('tenant', JSON.stringify(tenant));
}

export function clearUser() {
  sessionStorage.removeItem('tenant');
}

export function isAuthenticated() {
  return !!getUser();
}

export function isAdmin() {
  const tenant = getUser();
  return tenant && tenant?.role === 'admin';
}
