package org.gradle.examples.web;

import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;


import static org.junit.Assert.assertEquals;

public class ServletTest {

    private ServletTester servletTester;

	@Before
	public void setup() throws Exception {
		this.servletTester = new ServletTester();
		servletTester.addServlet(org.gradle.examples.web.Servlet.class, "/");
        servletTester.start();
	}

	@Test
	public void doGet() throws Exception {
		HttpTester request = new HttpTester();
		request.setMethod("GET");
		request.setURI("/");
		request.setVersion("HTTP/1.0");

		HttpTester response = new HttpTester();
		response.parse(servletTester.getResponses(request.generate()));

		assertEquals(200,response.getStatus());
		assertEquals("hello, world",response.getContent());
		assertEquals("yes",response.getHeader("did-it-work"));
	}
}