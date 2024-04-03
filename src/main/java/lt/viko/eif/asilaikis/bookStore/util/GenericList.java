package lt.viko.eif.asilaikis.bookStore.util;

import lt.viko.eif.asilaikis.bookStore.model.Book;
import lt.viko.eif.asilaikis.bookStore.model.Client;
import lt.viko.eif.asilaikis.bookStore.model.Order;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement(name = "OrderList")
@XmlSeeAlso({Order.class, Client.class, Book.class})
public class GenericList <T> {
    private List<T> Value;
    public GenericList() {}
    @XmlAnyElement(lax = true)
    public List<T> getValue() {
        return Value;
    }
    public void setValue(List<T> Value) {
        this.Value = Value;
    }
}
