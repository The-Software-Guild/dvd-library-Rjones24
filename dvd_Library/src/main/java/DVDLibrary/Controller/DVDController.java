package DVDLibrary.Controller;

import DVDLibrary.dao.DVDDao;
import DVDLibrary.dao.DVDDaoException;
import DVDLibrary.dto.DVD;
import DVDLibrary.ui.DVDView;

import java.util.List;

public class DVDController {

    private final DVDView view;
    private final DVDDao dao;

    public DVDController( DVDDao dao,DVDView view){
        this.dao = dao;
        this.view =view;
    }

    public void run()  {
        boolean keepGoing = true;
        try {
            while (keepGoing) {
                int menuSelection;
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1 -> listDvds();
                    case 2 -> createDvd();
                    case 3 -> viewDvd();
                    case 4 -> removeDvd();
                    case 5 -> editDvd();
                    case 6 -> SearchDvd();
                    case 7 -> keepGoing = false;
                }
            }
            exitMessage();
        } catch (DVDDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void SearchDvd() throws DVDDaoException {
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.SearchDvd(dvd);
    }

    private void editDvd() throws DVDDaoException {
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        DVD editDVD = view.displayEdit(dvd);
        dao.addDVD(dvdTitle,editDVD);
        view.displayEditResult(editDVD);
    }

    private void removeDvd() throws DVDDaoException {
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(dvd);
    }

    private void viewDvd() throws DVDDaoException {
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDvd(dvd);
    }

    private void createDvd() throws DVDDaoException {
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd.getTitle(),newDvd);
    }

    private void listDvds() throws DVDDaoException {
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDvdList(dvdList);
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
