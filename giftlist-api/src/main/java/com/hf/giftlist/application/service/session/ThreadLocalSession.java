package com.hf.giftlist.application.service.session;

import com.hf.giftlist.domain.model.login.Session;

public class ThreadLocalSession {

    private static ThreadLocal<Session> session = new ThreadLocal<>();

    public static void setSession(final Session value) {
        session.set(value);
    }

    public static Session getSession() {
        return session.get();
    }

    public static void clear() {
        session.remove();
    }
}
