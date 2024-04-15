import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;

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
            if (collectionName == null || collectionName == "") {
                throw new MyUncheckedException("Нет названия!");
            }
        } catch (MyUncheckedException e) {
            System.out.print("Обработка исключения: " + e.getMessage() + "      ");

        } finally {
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
