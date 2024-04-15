import java.util.Arrays;
import java.io.*;


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