import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class LibraryManagementSystem 
{
    static class Book 
    {
        int bookId;
        String title;
        String author;

        public Book(int bookId, String title, String author) 
        {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() 
        {
            return "BookID: " + bookId + ", Title: \"" + title + "\", Author: " + author;
        }
    }

    // Linear Search by title
    public static void linearSearch(Book[] books, String searchTitle) 
    {
        boolean found = false;
        for (Book book : books) 
        {
            if (book.title.equalsIgnoreCase(searchTitle)) 
            {
                System.out.println("Book Found (Linear Search): " + book);
                found = true;
                break;
            }
        }
        if (!found) 
        {
            System.out.println("Book not found (Linear Search).");
        }
    }

    // Binary Search by title (assuming sorted array)
    public static void binarySearch(Book[] books, String searchTitle) 
    {
        int low = 0, high = books.length - 1;
        while (low <= high) 
        {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(searchTitle);
            if (cmp == 0) 
            {
                System.out.println("Book Found (Binary Search): " + books[mid]);
                return;
            } 
            else if (cmp < 0) 
            {
                low = mid + 1;
            } 
            else 
            {
                high = mid - 1;
            }
        }
        System.out.println("Book not found (Binary Search).");
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        Book[] books = new Book[5];
        books[0] = new Book(1, "Java Programming", "James Gosling");
        books[1] = new Book(2, "Python Basics", "Guido van Rossum");
        books[2] = new Book(3, "Data Structures", "Robert Lafore");
        books[3] = new Book(4, "C++ Fundamentals", "Bjarne Stroustrup");
        books[4] = new Book(5, "Artificial Intelligence", "Stuart Russell");

        System.out.print("Enter title to search (Linear Search): ");
        String title1 = sc.nextLine();
        linearSearch(books, title1);

        Arrays.sort(books, Comparator.comparing(book -> book.title.toLowerCase()));

        System.out.print("\nEnter title to search (Binary Search): ");
        String title2 = sc.nextLine();
        binarySearch(books, title2);

        sc.close();
    }
}

