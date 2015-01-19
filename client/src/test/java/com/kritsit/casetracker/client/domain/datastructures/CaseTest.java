package com.kritsit.casetracker.client.domain.datastructures;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;

public class CaseTest extends TestCase {

    public CaseTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(CaseTest.class);
    }

    public void setUp() {
        Incident incident = new Incident("100 Long Street, Cape Town", "Western Cape", new Date("2015-01-19"), new Date("2015-02-19"), false);
    }
}