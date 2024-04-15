import java.util.Arrays;
import java.io.*;

// Объявляемое исключение
class MyDeclaredException extends Exception {
    public MyDeclaredException(String message) {
        super(message);
    }
}


// Необъявляемое исключение
class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}

// Интерфейс, описывающий методы доступа к полям объектов и функциональный метод
interface MyInterface {

    String getStringField() throws MyUncheckedException;

    int getTotalPagesInSeries() throws MyDeclaredException;


    void output(OutputStream out) throws IOException;

    void write(Writer out) throws IOException;
}

class StreamHandler {
    public static void output(MyInterface o, OutputStream out) throws IOException {
        o.output(out);
    }

    public static MyInterface input(InputStream in) throws IOException{
        try (ObjectInputStream objectInputStream = new ObjectInputStream(in)){
            return (MyInterface) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при чтении объекта из потока");
        }
    }


    public static void write(MyInterface o, Writer out) throws IOException {
        o.write(out);
    }

    public static MyInterface read(Reader in) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(in)){
            String line = bufferedReader.readLine();
            String[] data = line.split(":");
            return (MyInterface) new CustomMyInterface(data[0], Integer.parseInt(data[1]));
        }
    }

    public static void serialiseInterface(MyInterface o, OutputStream outputStream)throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);
    }

    public static MyInterface deserialiseInterface(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (MyInterface) objectInputStream.readObject();
    }
}

// Класс "Серия сочинений" со своей функциональной семантикой
class VolumeOfWorks implements MyInterface {
    private int[] pagesInBooks;
    private String seriesName;
    private int introPages;

    public VolumeOfWorks() {
    }

    public VolumeOfWorks(int[] pagesInBooks, String seriesName, int introPages) {
        this.pagesInBooks = pagesInBooks;
        this.seriesName = seriesName;
        this.introPages = introPages;
    }

    @Override
    public String toString() {
        return "VolumeOfWorks { " +
                " pagesInBooks = " + Arrays.toString(pagesInBooks) +
                ", seriesName= " + seriesName + " " +
                ", introPages = " + introPages +
                '}';
    }

    @Override
    public String getStringField() {
        try {
            if (seriesName == null || seriesName == ""){
                throw new MyUncheckedException("Нет названия!");
            }
        }
        catch (MyUncheckedException e){
            System.out.print("Обработка исключения: " + e.getMessage() + "      ");

        }
        finally {
            return seriesName;
        }
    }

    @Override
    public int getTotalPagesInSeries() {
        try {
            // Проверка количества страниц на 0
            if (pagesInBooks == null || pagesInBooks.length == 0) {
                throw new MyDeclaredException("Нет страниц в книгах!");
            }

            return Arrays.stream(pagesInBooks).sum();
        } catch (MyDeclaredException e) {
            System.out.print("Обработка исключения: " + e.getMessage() + "        ");
            return 0;
        }
    }

    public void output(OutputStream out) throws IOException {
        out.write(Arrays.toString(pagesInBooks).getBytes());
        out.write(seriesName.getBytes());
        out.write(String.valueOf(introPages).getBytes());
    }

    public void write(Writer out) {
        // вызывается конкретный метод для записи данных в поток
        try (BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

// Класс "Сборник статей" со своей функциональной семантикой
class ArticleCollection implements MyInterface {
    private int[] pagesInArticles;
    private String collectionName;
    private int maxAnnotationPages;

    public ArticleCollection() {
    }

    public ArticleCollection(int[] pagesInArticles, String collectionName, int maxAnnotationPages) {
        this.pagesInArticles = pagesInArticles;
        this.collectionName = collectionName;
        this.maxAnnotationPages = maxAnnotationPages;
    }

    @Override
    public String getStringField() {
        try {
            if (collectionName == null || collectionName == ""){
                throw new MyUncheckedException("Нет названия!");
            }
        }
        catch (MyUncheckedException e){
            System.out.print("Обработка исключения: " + e.getMessage() + "      ");

        }
        finally {
            return collectionName;
        }
    }

    @Override
    public String toString() {
        return "ArticleCollection { " +
                " pagesInArticles = " + Arrays.toString(pagesInArticles) +
                ", collectionName = " + collectionName + " " +
                ", maxAnnotationPages = " + maxAnnotationPages +
                '}';
    }

    @Override
    public int getTotalPagesInSeries() {
        try {
            // Проверка количества страниц на 0
            if (pagesInArticles == null || pagesInArticles.length == 0) {
                throw new MyDeclaredException("Нет страниц в статьях!");
            }

            return Arrays.stream(pagesInArticles).sum();
        } catch (MyDeclaredException e) {
            System.out.println("Обработка исключения: " + e.getMessage());
            return 0; // Можно вернуть 0 или другое значение по умолчанию при возникновении исключения
        }
    }

    public void output(OutputStream out) throws IOException {
        out.write(Arrays.toString(pagesInArticles).getBytes());
        out.write(collectionName.getBytes());
        out.write(String.valueOf(maxAnnotationPages).getBytes());
    }

    public void write(Writer out) throws IOException {
       out.write(Arrays.toString(pagesInArticles));
       out.write(collectionName);
       out.write(String.valueOf(maxAnnotationPages));
    }
}

class OutputHandler {
    public void add(MyInterface volume, MyInterface articleCollection) {
        try {
            // Вызов метода output для объекта VolumeOfWorks
            System.out.println("Output для VolumeOfWorks:");
            try (FileOutputStream fileOutputStream = new FileOutputStream("output_volume.txt")) {
                volume.output(fileOutputStream);
            }

            // Вызов метода write для объекта VolumeOfWorks
            System.out.println("Write для VolumeOfWorks:");
            try (BufferedWriter volumeWriter = new BufferedWriter(new FileWriter("write_volume.txt"))) {
                volume.write(volumeWriter);
            }

            // Вызов метода output для объекта ArticleCollection
            System.out.println("Output для ArticleCollection:");
            try (FileOutputStream fileOutputStream = new FileOutputStream("output_article.txt")) {
                articleCollection.output(fileOutputStream);
            }

            // Вызов метода write для объекта ArticleCollection
            System.out.println("Write для ArticleCollection:");
            try (BufferedWriter articleWriter = new BufferedWriter(new FileWriter("write_article.txt"))) {
                articleCollection.write(articleWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class UserInterface{
    public void doUserInterface() throws IOException, ClassNotFoundException {
        MyInterface myInterface = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n\n\nВыберите тип (1 - VolumeOfWorks, 2 - ArticleCollection) : ");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Введите следующие данные\n" + "Kоличество страниц: ");
                    String lineBook = reader.readLine();
                    String[] parts1 = lineBook.split(" ");
                    int[] pagesInBooks = new int[parts1.length];
                    for (int i = 0; i < parts1.length; i++) {
                        pagesInBooks[i] = Integer.parseInt(parts1[i]);
                    }
                    System.out.println("Название серии: ");
                    String nameBook = reader.readLine();
                    System.out.println("Количество вводных станиц: ");
                    int intro = Integer.parseInt(reader.readLine());
                    myInterface = new VolumeOfWorks(pagesInBooks, nameBook, intro);
                    break;
                case 2:
                    System.out.println("Введите следующие данные\n" + "Kоличество страниц: ");
                    String lineArticle = reader.readLine();
                    String[] parts2 = lineArticle.split(" ");
                    int[] pagesInArticle = new int[parts2.length];
                    for (int i = 0; i < parts2.length; i++) {
                        pagesInArticle[i] = Integer.parseInt(parts2[i]);
                    }
                    System.out.println("Название серии: ");
                    String name = reader.readLine();
                    System.out.println("Количество станиц аннотации: ");
                    int annotation = Integer.parseInt(reader.readLine());
                    myInterface = new ArticleCollection(pagesInArticle, name, annotation);
                    break;
                default:
                    System.out.println("Error!");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StreamHandler.output(myInterface, byteArrayOutputStream);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            myInterface = StreamHandler.input(byteArrayInputStream);

            FileWriter fileWriter = new FileWriter("output.txt");
            StreamHandler.write(myInterface, fileWriter);

            FileReader fileReader = new FileReader("output.txt");
            myInterface = StreamHandler.read(fileReader);

            FileOutputStream fileOutputStream = new FileOutputStream("serialized.dat");
            StreamHandler.serialiseInterface(myInterface, fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream("serialised.dat");
            MyInterface deserialisedInterface = StreamHandler.deserialiseInterface(fileInputStream);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
}

class CustomMyInterface{

    public CustomMyInterface(String s, int parseInt) {

    }
}


public class Main {
    public static void main(String[] args) throws MyDeclaredException, IOException, ClassNotFoundException {
        // Создание базы объектов типа интерфейс
        MyInterface[] objectsArray = new MyInterface[5];
        objectsArray[0] = new VolumeOfWorks(new int[]{100, 200, 150}, "", 20);
        objectsArray[1] = new ArticleCollection(new int[]{10, 15, 20}, "Science Journal", 5);
        objectsArray[2] = new VolumeOfWorks(new int[]{100, 200, 150}, "Literary Classics", 20);
        objectsArray[3] = new ArticleCollection(new int[]{25, 30, 40}, "Technology Magazine", 10);
        objectsArray[4] = new VolumeOfWorks(new int[]{}, "ABC", 0);

        // Вывод полной информации об объектах массива
        System.out.println("Полная информация:");
        for (MyInterface obj : objectsArray) {
            System.out.println();
            System.out.println(obj.toString());
        }

        // Вывод информации об общем количестве страниц в серии
        System.out.println("Вывод информации об общем количестве страниц в серии");
        for (MyInterface obj : objectsArray) {
            if (obj instanceof VolumeOfWorks) {
                VolumeOfWorks volume = (VolumeOfWorks) obj;
                System.out.println("Общее количество страниц в серии " + volume.getStringField() + ": " + volume.getTotalPagesInSeries());
            }
        }

        // Создание объектов
        VolumeOfWorks volume = new VolumeOfWorks(new int[]{100, 200, 150}, "Classic Novels", 10);
        ArticleCollection articleCollection = new ArticleCollection(new int[]{20, 30, 40}, "Research Papers", 5);

        // Создание экземпляра OutputHandler
        OutputHandler outputHandler = new OutputHandler();

        // Вызов метода add для обработки вывода
        outputHandler.add(volume, articleCollection);

        UserInterface userInterface = new UserInterface();
        userInterface.doUserInterface();
    }

}