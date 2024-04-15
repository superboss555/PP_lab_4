package Laba.Handler;

import Laba.Interface.CustomMyInterface;
import Laba.Interface.MyInterface;

import java.io.*;

public class StreamHandler {
    public static void output(MyInterface o, OutputStream out) throws IOException {
        o.output(out);
    }

    public static MyInterface input(InputStream in) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(in)) {
            return (MyInterface) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при чтении объекта из потока");
        }
    }


    public static void write(MyInterface o, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        o.write(fileWriter);
        byteArrayOutputStream.close();
        fileWriter.close();
    }

    public static MyInterface read() throws IOException {
        FileReader in = new FileReader("output.txt");
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            String line = bufferedReader.readLine();
            String[] data = line.split(":");
            return (MyInterface) new CustomMyInterface(data[0], Integer.parseInt(data[1]));
        }
    }

    public static void serialiseInterface(MyInterface o)  {
        /*ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);*/
        try {
            FileOutputStream outputStream = new FileOutputStream("serialized.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static MyInterface deserialiseInterface(InputStream inputStream) throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        return (MyInterface) objectInputStream.readObject();
        try {
            FileInputStream fileInputStream = new FileInputStream("serialised.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            MyInterface myInterface = (MyInterface) objectInputStream.readObject();

            System.out.println(myInterface);
            return myInterface;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
