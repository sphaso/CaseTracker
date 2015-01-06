package com.kritsit.casetracker.client.domain.services;

import com.kritsit.casetracker.client.domain.Domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServerLoginTest extends TestCase {
    ILoginService loginService;

    public ServerLoginTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(ServerLoginTest.class);
    }

    public void setUp() {
        IConnectionService connection = Domain.getServerConnection();
        loginService = new ServerLogin(connection);
    }

    public void testCreation() {
        assertTrue(loginService instanceof ILoginService);
    }

    public void testLoginAttempt_Failed() {
        char[] password = {'i', 'n', 's', 'p', 'e', 'c', 't', 'o', 'r'};
        String username = "inspector";
        boolean succeeded = loginService.login(username, password);
        assertFalse(succeeded);
    }
}
