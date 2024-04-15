import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;

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
            if (seriesName == null || seriesName == "") {
                throw new MyUncheckedException("Нет названия!");
            }
        } catch (MyUncheckedException e) {
            System.out.print("Обработка исключения: " + e.getMessage() + "      ");

        } finally {
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
