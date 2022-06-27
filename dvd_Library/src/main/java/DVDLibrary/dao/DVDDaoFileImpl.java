package DVDLibrary.dao;

import DVDLibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDDaoFileImpl implements DVDDao{

    public static final String FILE = "dvd.txt";
    public static final String DELIMITER = "::";
    private final Map<String, DVD> dvds = new HashMap<>();

    private void loader() throws DVDDaoException{
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDDaoException("-_- Could not load1 data into memory.", e);
        }
        String currentLine;
        DVD currentDvd;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    private DVD unmarshallDvd(String dvdAsText){

        String[] DvdTitles = dvdAsText.split(DELIMITER);

        String Title = DvdTitles[0];

        DVD dvdFromFile = new DVD(Title);

        dvdFromFile.setDate(DvdTitles[1]);
        dvdFromFile.setRating(DvdTitles[2]);
        dvdFromFile.setDirector(DvdTitles[3]);
        dvdFromFile.setStudio(DvdTitles[4]);
        dvdFromFile.setNotes(DvdTitles[5]);

        return dvdFromFile ;
    }

    private void Writer() throws DVDDaoException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new DVDDaoException(
                    "Could not save student data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDvd : dvdList) {
             dvdAsText = marshallDvd(currentDvd);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    private String marshallDvd(DVD aDvd){
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getDate() + DELIMITER;
        dvdAsText += aDvd.getRating() + DELIMITER;
        dvdAsText += aDvd.getDirector() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getNotes();
        return dvdAsText;
    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDDaoException {
        loader();
        DVD newDvd = dvds.put(title,dvd);
        Writer();
        return newDvd;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDDaoException {
       loader();
       return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDDaoException {
        loader();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDDaoException {
        loader();
        DVD removedDvd = dvds.remove(title);
        Writer();
        return removedDvd;
    }

    @Override
    public DVD EditDVD(String title, DVD dvd) throws DVDDaoException {
        loader();
        DVD newDvd = dvds.put(title,dvd);
        Writer();
        return newDvd;
    }

    @Override
    public DVD SearchDVD(String title) throws DVDDaoException {
        loader();
        return dvds.get(title);
    }
}
