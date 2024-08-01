package util;

import javax.servlet.http.HttpSession;

import entities.Member;

public class SessionUtil {
    public static Member getMember(HttpSession session) {
        if (session != null) {
            return (Member) session.getAttribute("member");
        }
        return null;
    }
    public static void setMember(HttpSession session, Member member) {
        if (session != null) {
             session.setAttribute("member", member);
        }
    }
    public static void destroy(HttpSession session) {
        if (session != null) {
             session.invalidate();
        }
    }
}
