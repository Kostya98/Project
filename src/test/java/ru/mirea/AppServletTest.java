package ru.mirea;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppServletTest {
    private AppServlet appServlet = new AppServlet();
    private StringWriter stringWriter = new StringWriter();
    private PrintWriter printWriter = new PrintWriter(stringWriter);
    private HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    private HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

    @Test
    public void getTag() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        appServlet.doGet(httpServletRequest, httpServletResponse);
        printWriter.flush();
        assertEquals("{\"offset\":0,\"tagPerPage\":10,\"tagListSize\":0,\"tagList\":[]}", stringWriter.toString());
    }

    @Test
    public void putAndSearchTag() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        when(httpServletRequest.getParameter("mnemo")).thenReturn("RUB");
        when(httpServletRequest.getParameter("name")).thenReturn("Russian+Ruble");
        appServlet.doPut(httpServletRequest, httpServletResponse);
        when(httpServletRequest.getParameter("search")).thenReturn("RU");
        appServlet.doGet(httpServletRequest, httpServletResponse);
        printWriter.flush();
        assertEquals("{\"offset\":0,\"tagPerPage\":10,\"tagListSize\":1,\"tagList\":[{\"mnemo\":\"RUB\",\"name\":\"Russian+Ruble\"}]}", stringWriter.toString());
    }

    @Test
    public void getOffset() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        when(httpServletRequest.getParameter("mnemo")).thenReturn("RUB");
        when(httpServletRequest.getParameter("name")).thenReturn("Russian+Ruble");
        appServlet.doPut(httpServletRequest, httpServletResponse);
        when(httpServletRequest.getParameter("offset")).thenReturn("1");
        appServlet.doGet(httpServletRequest, httpServletResponse);
        printWriter.flush();
        assertEquals("{\"offset\":1,\"tagPerPage\":10,\"tagListSize\":1,\"tagList\":[]}", stringWriter.toString());
    }

    @Test
    public void deleteTag() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        when(httpServletRequest.getParameter("mnemo")).thenReturn("RUB");
        when(httpServletRequest.getParameter("name")).thenReturn("Russian+Ruble");
        appServlet.doPut(httpServletRequest, httpServletResponse);
        when(httpServletRequest.getParameter("mnemo")).thenReturn("RUB");
        appServlet.doDelete(httpServletRequest, httpServletResponse);
        printWriter.flush();
        assertEquals("", stringWriter.toString());
    }
}