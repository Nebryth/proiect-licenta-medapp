import SessionManager from "../services/sessionManager";

export default function Logout() {
  SessionManager.clearSession();
  window.location.replace("/");
  return null;
}
