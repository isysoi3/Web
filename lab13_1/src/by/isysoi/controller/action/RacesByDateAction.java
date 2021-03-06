package by.isysoi.controller.action;

import by.isysoi.controller.NavigationConstants;
import by.isysoi.dao.RaceDAOInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RacesByDateAction implements Action {


    @Override
    public String getPattern() {
        return "racesByDate";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(NavigationConstants.racesPage);
        String date = request.getParameter("date");
        if (date != null) {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            RaceDAOInterface raceDAO = (RaceDAOInterface) servletContext.getAttribute("raceDAO");
            try {
                List list = raceDAO.readRacesByDate(ft.parse(date));
                request.setAttribute("racesByDateList", list);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dispatcher.forward(request, response);
    }

}
