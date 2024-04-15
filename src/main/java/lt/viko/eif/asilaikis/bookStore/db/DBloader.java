package lt.viko.eif.asilaikis.bookStore.db;

import lt.viko.eif.asilaikis.bookStore.model.Book;
import lt.viko.eif.asilaikis.bookStore.model.Client;

import lt.viko.eif.asilaikis.bookStore.model.Order;
import lt.viko.eif.asilaikis.bookStore.util.HibernateUtil;
import lt.viko.eif.asilaikis.bookStore.util.JAXBUtil;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBloader {
    public static void setupDB ()
    {
        Client client1 = new Client("Arnas", "Silaikis");
        Client client2 = new Client("Jonas", "Petraitis");
        Client client3 = new Client("Eglė", "Kazlauskaitė");
        Client client4 = new Client("Tomas", "Juška");
        Client client5 = new Client("Gabija", "Stankevičiūtė");

        Book book1 = new Book("Norvegų giria", "Haruki", "Murakami", "15,39");
        Book book2 = new Book("Knygų vagilė", "Markus", "Zusak", "9,46");
        Book book3 = new Book("Alchemikas", " Paulo", "Coelho", "11,91");
        Book book4 = new Book("Mažasis princas", "Antoine", "de Saint-Exupéry", "8,85");
        Book book5 = new Book("1984", "George", "Orwell", "10,43");

        Order order1 = new Order(client1, book3);
        Order order2 = new Order(client4, book1);
        Order order3 = new Order(client2, book5);
        Order order4 = new Order(client3, book2);
        Order order5 = new Order(client5, book4);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        org.h2.tools.Server server = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            server = Server.createTcpServer("-tcpPort", "9093").start();
            transaction = session.beginTransaction();
            orderList.forEach(session::save);
            transaction.commit();
        }
        catch (SQLException e)
        {
            if(transaction!=null)
            {
                transaction.rollback();
            }
        }
        finally
        {

            if (server != null) {
                server.shutdown();
            }
        }
    }

    public static List<Order> ListOrder()
    {
        try(Session session=HibernateUtil.getSessionFactory().openSession())
        {
            List<Order> orders = session.createQuery("from Order", Order.class).list();
            orders.forEach(System.out::println);
            return orders;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Client> ListClient()
    {
        try(Session session=HibernateUtil.getSessionFactory().openSession())
        {
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            clients.forEach(System.out::println);
            return clients;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Book> ListBook()
    {
        try(Session session=HibernateUtil.getSessionFactory().openSession())
        {
            List<Book> books = session.createQuery("from Book", Book.class).list();
            books.forEach(System.out::println);
            return books;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String ListOrderXML() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Order> orders = session.createQuery("from Order", Order.class).list();
            return ListXML(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public static <T> String ListXML(T data) {
        try {
            return JAXBUtil.marshal(data, new File("JAXB.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }


    }
}

