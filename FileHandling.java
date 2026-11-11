import java.io.*;
import java.util.*;

public class FileHandling {

    // Save method
    public static void saveBooksToFile(String fileName, ArrayList<Book> bookList) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < bookList.size(); i++) {
                Book b = bookList.get(i);
                bw.write(b.getFileData());
                bw.newLine();
            }

            bw.close();
            System.out.println("Saved!");
        } catch (Exception e) {
            System.out.println("Error while saving file.");
        }
    }

    // Load method
    public static ArrayList<Book> loadBooksFromFile(String fileName) {
        ArrayList<Book> bookList = new ArrayList<>();
        File f = new File(fileName);

        if (!f.exists()) return bookList;

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] d = line.split(",");

                if (d.length == 5) {
                    Book b = new Book(d[0], d[1], d[2], d[3], Boolean.parseBoolean(d[4]));
                    bookList.add(b);
                }
            }
            sc.close();
            System.out.println("Loaded!");
        } catch (Exception e) {
            System.out.println("Error while loading file.");
        }

        return bookList;
    }
}