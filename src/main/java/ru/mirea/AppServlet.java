package ru.mirea;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AppServlet extends HttpServlet {
    private Dictionary dictionary = new Dictionary();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private int tagPerPage = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Object response;
        try {
            String search = req.getParameter("search");
            List tagList;
            if (search != null) {
                if (search.length() < 2) {
                    throw new Exception("Неправильные параметры запроса");
                }
                tagList = dictionary.findTag(search);
            } else {
                tagList = dictionary.getTag();
            }
            String offset = req.getParameter("offset");
            int offsetInt = 0;
            if (offset != null) {
                try {
                    offsetInt = Integer.parseInt(offset);
                } catch (NumberFormatException e) {
                    throw new Exception("Неправильные параметры запроса");
                }
                if (offsetInt < 0) {
                    throw new Exception("Неправильные параметры запроса");
                }
            }
            response = new Response(offsetInt, tagPerPage, tagList);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response = new AppException(e.getMessage());
        }
        synchronized (objectMapper) {
            objectMapper.writeValue(resp.getWriter(), response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String mnemo = req.getParameter("mnemo");
        String name = req.getParameter("name");
        try {
            dictionary.putTag(mnemo, name);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            synchronized (objectMapper) {
                objectMapper.writeValue(resp.getWriter(), new AppException(e.getMessage()));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String mnemo = req.getParameter("mnemo");
        try {
            dictionary.deleteTag(mnemo);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            synchronized (objectMapper) {
                objectMapper.writeValue(resp.getWriter(), new AppException(e.getMessage()));
            }
        }
    }
}