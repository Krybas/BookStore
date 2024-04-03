package lt.viko.eif.asilaikis.bookStore.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.List;

public class JAXBUtil {
    public static <T> String marshal(T value, File path) {
        try {
            StringWriter string = new StringWriter();
            GenericList<T> Value = new GenericList<T>();
            Value.setValue((List<T>) value);
            JAXBContext jaxbContext = JAXBContext.newInstance(value.getClass(), Value.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(Value, path);
            jaxbMarshaller.marshal(Value, string);

            return string.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T unmarshal(Class<T> tClass, String filePath) {
        T obj;
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            obj = tClass.cast(unmarshaller.unmarshal(new File(filePath)));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }
}
