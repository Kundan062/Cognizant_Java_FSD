class LibraryBook {
    int bookId;
    String title;
    String author;

    LibraryBook(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}

public class LibraryManagementSystem {

    static int linearSearch(LibraryBook[] books, String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.equals(title))
                return i;
        }
        return -1;
    }

    static int binarySearch(LibraryBook[] books, String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = books[mid].title.compareTo(title);

            if (result == 0)
                return mid;
            else if (result < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        LibraryBook[] books = {
                new LibraryBook(1, "C", "Author1"),
                new LibraryBook(2, "Java", "Author2"),
                new LibraryBook(3, "Python", "Author3")
        };

        System.out.println(binarySearch(books, "Java"));
    }
}