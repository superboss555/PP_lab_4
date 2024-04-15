package Laba.Interface;

import Laba.Handler.StreamHandler;
import Laba.Model.ArticleCollection;
import Laba.Model.VolumeOfWorks;

import java.io.*;

public class UserInterface {
    public void doUserInterface() throws IOException, ClassNotFoundException {
        MyInterface myInterface = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n\n\nВыберите тип (1 - model.VolumeOfWorks, 2 - model.ArticleCollection) : ");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Введите следующие данные\n" + "Kоличество страниц: ");
                    String lineBook = reader.readLine();
                    String[] parts1 = lineBook.split(" ");
                    int[] pagesInBooks = new int[parts1.length];
                    for (int i = 0; i < (parts1.length - 1); i++) {
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
            // запись в байтовый поток
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StreamHandler.output(myInterface, byteArrayOutputStream);

            // запись символьный поток
//            StreamHandler.write(myInterface, byteArrayOutputStream);


//            myInterface = StreamHandler.read();


//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            myInterface = StreamHandler.input(byteArrayInputStream);
//            System.out.println("\n///" + myInterface);


            StreamHandler.serialiseInterface(myInterface);

            MyInterface deserialisedInterface = StreamHandler.deserialiseInterface();
            System.out.println("Десериализовано: " + deserialisedInterface);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
