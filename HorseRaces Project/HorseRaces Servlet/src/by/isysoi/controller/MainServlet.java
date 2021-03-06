package by.isysoi.controller;

import by.isysoi.controller.action.Action;
import by.isysoi.controller.action.get.*;
import by.isysoi.controller.action.post.*;
import by.isysoi.exception.ActionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main servlet of app
 *
 * @author Ilya Sysoi
 */
@WebServlet
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Map<String, Action> getActions;
    private Map<String, Action> postActions;

    public MainServlet() {
        super();
        this.getActions = new HashMap<>();
        this.postActions = new HashMap<>();
    }

    @Override
    public void init() {
        Action[] getActions = {
                new InfoPageGetAction(),
                new HomeGetAction(),
                new LoginGetAction(),
                new WinnersByRaceGetAction(),
                new RacesByDateGetAction(),
                new HorsesInRaceGetAction(),
                new RegistrationGetAction(),
                new SaveResultGetAction(),
                new ChatGetAction()
        };
        for (Action c : getActions) {
            this.getActions.put(c.getPattern(), c);
        }

        Action[] postActions = {
                new LoginPostAction(),
                new LogoutAction(),
                new SaveResultPostAction(),
                new RegistrationPostAction(),
                new RemoveHorsePostAction()
        };
        for (Action c : postActions) {
            this.postActions.put(c.getPattern(), c);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            getActions.get(action).execute(request, response, this.getServletContext());
        } catch (ActionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            postActions.get(action).execute(request, response, this.getServletContext());
        } catch (ActionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            response.sendError(500);
        }
    }

}
