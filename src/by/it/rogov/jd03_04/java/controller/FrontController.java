package by.it.rogov.jd03_04.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet{

    ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory=new ActionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        serv(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        serv(req,resp);
    }

    private void serv(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        Actions action = actionFactory.defineAction(req);
        Cmd nextAction = action.cmd.execute(req);
        if (nextAction==null){
            //show view jsp
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(action.jsp);
            requestDispatcher.forward(req,resp);
        }
        else
        {
            //redirect wb to other cmd
            resp.sendRedirect("do?command="+nextAction.toString().toLowerCase());
        }
    }
}
