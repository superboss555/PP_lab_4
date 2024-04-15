package Laba.Interface;

import Laba.Exception.MyDeclaredException;
import Laba.Exception.MyUncheckedException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

// Интерфейс, описывающий методы доступа к полям объектов и функциональный метод
public interface MyInterface extends Serializable {

    String getStringField() throws MyUncheckedException;

    int getTotalPagesInSeries() throws MyDeclaredException;


    void output(OutputStream out) throws IOException;

    void write(Writer out) throws IOException;
}
