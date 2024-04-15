1. Модифицировать классы из лабораторной работы №3 следующим образом:
-в интерфейс добавить следующие методы:
-- записи в байтовый поток
void output(OutputStream out);
-- записи в символьный поток 
void write(Writer out);
-реализовать добавленные в интерфейс методы в обоих классах;
-описать класс со следующими статическими методами:
-- записи в байтовый поток
void output<Интерфейс>(<Интерфейс> o, OutputStream out);
-- чтения из байтового потока 
<Интерфейс> input<Интерфейс>(InputStream in);
-- записи в символьный поток 
void write<Интерфейс> (<Интерфейс> o, Writer out);
-- чтения из символьного потока 
<Интерфейс> read<Интерфейс>(Reader in);
В статических методах записи делегировать вызов соответствующему методу интерфейса.
В обоих случаях записанный объект должен представлять собой последовательность значений своих полей.
В случае символьного потока рекомендуется считать, что один объект записывается в одну строку (поля разделены пробелами). Для чтения элемента из символьного потока можно  использовать класс StreamTokenizer. или BufferesReader. 

2. Модифицировать классы в иерархии таким образом, чтобы они были сериализуемы. Добавить в класс со статическими методами методы для вывода/ввода сериализованных объектов.
-- вывод сериализованных объектов 
void serialize<Интерфейс> (<Интерфейс> o, OutputStream out);
-- ввод десериализованного объекта 
<Интерфейс> deserialize<Интерфейс>(InputStream in);


3. Организовать примитивный интерфейс пользователя (заполнение базы элементов с консоли, выбор типа элемента пользователем). Протестировать разработанные методы работы с потоками, а также возможности сериализации.

