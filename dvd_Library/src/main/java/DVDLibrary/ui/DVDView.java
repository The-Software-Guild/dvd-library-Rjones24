package DVDLibrary.ui;

import DVDLibrary.dto.DVD;

import java.util.List;

public class DVDView {

    private final UserIO io;
    public DVDView(UserIO io){this.io = io;}

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. See All DVDs in stock");
        io.print("2. Add a new DVD");
        io.print("3. View a specific DVD");
        io.print("4. Remove a DVD from stock");
        io.print("5. Edit information about a DVD from stock");
        io.print("6. Search to see if we have a DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getNewDVDInfo(){

        DVD CurrentDvd = new DVD(io.readString("Please enter the title of the dvd"));
        CurrentDvd.setDate(io.readString("Please enter the date the dvd was released"));
        CurrentDvd.setRating(io.readString("Please enter the dvds rating"));
        CurrentDvd.setDirector(io.readString("Please enter the director of the dvd"));
        CurrentDvd.setStudio(io.readString("Please enter the studio of the dvd"));
        CurrentDvd.setNotes(io.readString("Please enter the any notes you would like to add about this dvd"));
        return CurrentDvd;
    }

    public void displayDvdList(List<DVD> dvdList){
        for(DVD currentDvd : dvdList){
            String dvdInfo = String.format("%s : %s , %s , %s , %s , %s",
                    currentDvd.getTitle(),
                    currentDvd.getDate(),
                    currentDvd.getRating(),
                    currentDvd.getDirector(),
                    currentDvd.getStudio(),
                    currentDvd.getNotes());
            io.print((dvdInfo));
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the dvd title");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDate());
            io.print(dvd.getRating());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getNotes());
            io.print("");
        } else {
            io.print("No such dvd in collection.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void SearchDvd(DVD dvd) {
        if (dvd != null) {
            io.print("We have this dvd in are collection");
        } else {
            io.print("No such dvd in collection.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("dvd successfully removed.");
        }else{
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("dvd successfully Edited.");
        }else{
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public DVD displayEdit( DVD dvd ) {
        io.print("You are now editing the dvd titled " + dvd.getTitle());
        dvd.setDate(io.readString("the current date for the dvd is " + dvd.getDate() + " Please enter the edited date the dvd was released"));
        dvd.setRating(io.readString("the current rating for the dvd is " + dvd.getRating() + " Please enter the edited dvds rating"));
        dvd.setDirector(io.readString("the current director for the dvd is" + dvd.getDirector() + "Please enter the edited director of the dvd"));
        dvd.setStudio(io.readString("the current studio for the dvd is " + dvd.getStudio() + " Please enter the edited studio of the dvd"));
        dvd.setNotes(io.readString("the current notes for the dvd are " + dvd.getNotes()+ " please enter the edited notes this dvd"));
        return dvd;
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
}
