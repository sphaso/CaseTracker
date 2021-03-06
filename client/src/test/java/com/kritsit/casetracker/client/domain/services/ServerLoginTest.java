package com.kritsit.casetracker.client.domain.services;

import com.kritsit.casetracker.client.domain.Domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ServerLoginTest extends TestCase {
    IConnectionService connection;
    ILoginService loginService;

    public ServerLoginTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(ServerLoginTest.class);
    }

    public void setUp() {
        connection = Domain.getServerConnection();
        loginService = new ServerLogin(connection);
    }

    public void testCreation() {
        assertTrue(loginService instanceof ILoginService);
    }

    public void testLoginAttempt_ConnectionClosed() {
        char[] password = {'i', 'n', 's', 'p', 'e', 'c', 't', 'o', 'r'};
        String username = "inspector";
        boolean result = loginService.login(username, password);
        assertFalse(result);
    }

    public void testLoginAttempt_IncorrectUser() {
        connection.open("localhost", 1244);
        char[] password = {'i', 'n', 's', 'p', 'e', 'c', 't', 'o', 'r'};
        String username = "wrongInspector";
        boolean succeeded = loginService.login(username, password);
        assertFalse(succeeded);
    }

    public void testLoginAttempt_IncorrectPassword() {
        connection.open("localhost", 1244);
        char[] password = {'w', 'r', 'o', 'n', 'g', ' ', 'i', 'n', 's', 'p', 'e', 'c', 't', 'o', 'r'};
        String username = "inspector";
        boolean succeeded = loginService.login(username, password);
        assertFalse(succeeded);
    }

    public void testLoginAttempt_Succeeded() {
        connection.open("localhost", 1244);
        char[] password = {'i', 'n', 's', 'p', 'e', 'c', 't', 'o', 'r'};
        String username = "inspector";
        boolean succeeded = loginService.login(username, password);
        assertTrue(succeeded);
    }

    public void tearDown() {
        if (connection.isOpen()) {
            try {
                connection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Domain.resetServerConnection();
    }
}
