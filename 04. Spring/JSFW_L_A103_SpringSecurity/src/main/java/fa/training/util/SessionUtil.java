package fa.training.util;

import javax.servlet.http.HttpSession;

import fa.training.entity.Member;

public class SessionUtil {
    private static final String USER_SESSION_ATTRIBUTE = "loggedInUser";

    public static void setLoggedInUser(HttpSession session, Member member) {
	session.setAttribute(USER_SESSION_ATTRIBUTE, member);
    }

    public static Member getLoggedInUser(HttpSession session) {
	return (Member) session.getAttribute(USER_SESSION_ATTRIBUTE);
    }

    public static void removeLoggedInUser(HttpSession session) {
	session.removeAttribute(USER_SESSION_ATTRIBUTE);
    }

    public static boolean isLoggedIn(HttpSession session) {
	return getLoggedInUser(session) != null;
    }
}
