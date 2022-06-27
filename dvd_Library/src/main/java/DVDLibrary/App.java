package DVDLibrary;

import DVDLibrary.Controller.DVDController;
import DVDLibrary.dao.DVDDao;
import DVDLibrary.dao.DVDDaoFileImpl;
import DVDLibrary.ui.DVDView;
import DVDLibrary.ui.UserIO;
import DVDLibrary.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDView myView = new DVDView((myIo));
        DVDDao myDao = new DVDDaoFileImpl();
        DVDController controller = new DVDController(myDao,myView);
        controller.run();
    }
}
