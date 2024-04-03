package lt.viko.eif.asilaikis.bookStore.menu;

import lt.viko.eif.asilaikis.bookStore.db.DBloader;
import lt.viko.eif.asilaikis.bookStore.model.Order;
import lt.viko.eif.asilaikis.bookStore.util.JAXBUtil;

import java.util.Scanner;

public class Menu {
    public static void showMenu()
    {
        System.out.println("Make a selection");
        System.out.println("-----------------");
        System.out.printf("| 1)  %10s \n", " Load orders");
        System.out.printf("| 2)  %10s \n", " Load books");
        System.out.printf("| 3)  %10s \n", " Load clients");
        System.out.printf("| 4)  %10s \n", " Load order to XML");
        System.out.printf("| 5)  %10s \n", " Load XML to object");
        System.out.printf("| 6)  %7s \n", " Quit program");
    }
    public static void selectedMenu()
    {
        Scanner scan = new Scanner(System.in);
        boolean value = true;
        while (value)
        {
            int input = scan.nextInt();
            switch (input)
            {
                case 1:
                    DBloader.ListOrder();
                    showMenu();
                    break;
                case 2:
                    DBloader.ListBook();
                    showMenu();
                    break;
                case 3:
                    DBloader.ListClient();
                    showMenu();
                    break;
                case 4:
                    System.out.println(DBloader.ListOrderXML());
                    showMenu();
                    break;
                case 5:
                    DBloader.ListOrderXML();
                    showMenu();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    value = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;

            }
        }

    }
}
