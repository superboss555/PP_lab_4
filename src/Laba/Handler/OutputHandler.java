package Laba.Handler;

import Laba.Interface.MyInterface;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class OutputHandler {
    public void add(MyInterface volume, MyInterface articleCollection) {
        try {
            // Вызов метода output для объекта model.VolumeOfWorks
            System.out.println("Output для model.VolumeOfWorks:");
            try (FileOutputStream fileOutputStream = new FileOutputStream("output_volume.txt")) {
                volume.output(fileOutputStream);
            }

            // Вызов метода write для объекта model.VolumeOfWorks
            System.out.println("Write для model.VolumeOfWorks:");
            try (BufferedWriter volumeWriter = new BufferedWriter(new FileWriter("write_volume.txt"))) {
                volume.write(volumeWriter);
            }

            // Вызов метода output для объекта model.ArticleCollection
            System.out.println("Output для model.ArticleCollection:");
            try (FileOutputStream fileOutputStream = new FileOutputStream("output_article.txt")) {
                articleCollection.output(fileOutputStream);
            }

            // Вызов метода write для объекта model.ArticleCollection
            System.out.println("Write для model.ArticleCollection:");
            try (BufferedWriter articleWriter = new BufferedWriter(new FileWriter("write_article.txt"))) {
                articleCollection.write(articleWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
