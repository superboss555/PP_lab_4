import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

// Интерфейс, описывающий методы доступа к полям объектов и функциональный метод
interface MyInterface {

    String getStringField() throws MyUncheckedException;

    int getTotalPagesInSeries() throws MyDeclaredException;


    void output(OutputStream out) throws IOException;

    void write(Writer out) throws IOException;
}
