import lsm from "./localStorageManager";
import sidebarItems from "../components/sidebar-items";

const sessionKey = "userSession";

class SessionManager {
  static stringToObject = input => {
    return JSON.parse(input);
  };

  static objectToString = input => {
    return JSON.stringify(input);
  };

  static isSessionValid = () => {
    if (!SessionManager.hasUserSession()) return false;
  };

  static hasUserSession = () =>
    SessionManager.getUserSession() !== undefined &&
    SessionManager.getUserSession() !== null &&
    SessionManager.getUserProperty("error") === undefined;

  static getUserSession = (stringify = false) => {
    if (stringify) return lsm.getItem(sessionKey);
    return SessionManager.stringToObject(lsm.getItem(sessionKey));
  };

  static setUserSession = session => {
    lsm.setItem(sessionKey, SessionManager.objectToString(session));
  };

  static expireSession = () => {
    const session = SessionManager.getUserSession();
    Object.assign(session, {
      ...session,
      expirationDate: 0
    });
    SessionManager.setUserSession(session);
  };

  static clearSession = () => {
    lsm.removeItem(sessionKey);
  };

  static getUserProperty = property => {
    return SessionManager.stringToObject(lsm.getItem(sessionKey))[property];
  };

  static hasPermisionForUrl = pathname => {
    const userId = SessionManager.getUserProperty("user_id");
    let hasPermissions = false;
    const { prefix, items } = sidebarItems[userId];
    items.forEach(item => {
      if (`${prefix}${item.src}` === pathname) hasPermissions = true;
    });
    return hasPermissions;
  };
}

export default SessionManager;
