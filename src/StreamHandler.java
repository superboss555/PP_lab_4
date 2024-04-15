import java.io.*;

class StreamHandler {
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


    public static void write(MyInterface o, Writer out) throws IOException {
        o.write(out);
    }

    public static MyInterface read(Reader in) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            String line = bufferedReader.readLine();
            String[] data = line.split(":");
            return (MyInterface) new CustomMyInterface(data[0], Integer.parseInt(data[1]));
        }
    }

    public static void serialiseInterface(MyInterface o, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);
    }

    public static MyInterface deserialiseInterface(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (MyInterface) objectInputStream.readObject();
    }
}
