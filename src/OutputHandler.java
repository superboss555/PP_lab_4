import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

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
