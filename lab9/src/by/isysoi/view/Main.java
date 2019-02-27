package by.isysoi.view;

import by.isysoi.controller.HorseRaceController;
import by.isysoi.model.dao.ClientDAO;
import by.isysoi.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        var controller = new HorseRaceController();

//        var horses = controller.getHorsesByRaceId(1);
//        Utils.printList(horses);
//        var winners = controller.getWinnersByRaceId(2);
//        Utils.printListOfTuples(winners);
//
//        controller.updateResultForRace(1, 1, 3);
//
//        controller.addHorceToRace(3, 1);
//
//        try {
//
//
//            SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
//            var races = controller.getRacesByDate(ft.parse("11-02-2019"));
//            Utils.printList(races);
//
//
//
//        } catch (ParseException e) {
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }

        var tmp = new ClientDAO();
        Utils.printList(tmp.readClients());

    }

}