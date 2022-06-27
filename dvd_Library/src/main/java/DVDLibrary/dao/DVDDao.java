package DVDLibrary.dao;

import DVDLibrary.dto.DVD;
import java.util.List;

public interface DVDDao {

    DVD addDVD(String title, DVD dvd) throws DVDDaoException;

    List<DVD> getAllDVDs() throws DVDDaoException;

    DVD getDVD(String title) throws DVDDaoException;

    DVD removeDVD(String title) throws DVDDaoException;

    DVD EditDVD(String title, DVD dvd) throws DVDDaoException;

    DVD SearchDVD(String title) throws DVDDaoException;
}