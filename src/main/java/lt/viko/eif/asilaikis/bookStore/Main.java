package lt.viko.eif.asilaikis.bookStore;

import lt.viko.eif.asilaikis.bookStore.db.DBloader;
import lt.viko.eif.asilaikis.bookStore.menu.Menu;

public class Main {
    public static void main(String[] args) {
        //DBloader.setupDB();
        Menu.showMenu();
        Menu.selectedMenu();
    }
}