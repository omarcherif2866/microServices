package com.example.ms_session.services;

import com.example.ms_session.model.Session;

import java.util.Map;
import java.util.Set;

public interface ISessionservice {
    Session add(Session session);

    Session update(String id, Map<Object,Object> fields);

    boolean delete(String id);


    Session getSession(String id);

    public Set<Session> getAllSessions ();


}
