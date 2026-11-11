import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("******************************************");
        System.out.println("Student Name: Md. Mehedi Hasan");
        System.out.println("Student ID: 251-35-322");
        System.out.println("******************************************\n");

        Library library = new Library();
        String fileName = "books.txt";

        // Load whatever was saved from the last run, if the file exists
        library.loadBooksFromFile(fileName);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========== Library Management System ==========");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Display Books");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Save Data");
            System.out.println("8. Exit");
            System.out.print("Enter Your Choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 8.");
                continue;
            }

            switch (choice) {
                case 1:
                    // New book always starts out as available
                    System.out.print("Enter Book ID: ");
                    String bId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    library.addBook(new Book(bId, title, author, category, true));
                    break;

                case 2:
                    System.out.print("Enter Member Type (1 for Student, 2 for Faculty): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String mId = scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String mName = scanner.nextLine();

                    if (type.equals("1")) {
                        library.addMember(new StudentMember(mId, mName));
                    } else if (type.equals("2")) {
                        library.addMember(new FacultyMember(mId, mName));
                    } else {
                        System.out.println("Invalid member type selected.");
                    }
                    break;

                case 3:
                    library.displayAvailableBookDetails();
                    break;

                case 4:
                    System.out.print("Enter Book Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    try {
                        library.searchBook(searchTitle);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter Book ID to Borrow: ");
                    String borrowBId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String borrowMId = scanner.nextLine();

                    // Look both up first, then let the exceptions handle the "not found" cases
                    Book bToBorrow = library.findBookById(borrowBId);
                    Member mBorrower = library.findMemberById(borrowMId);

                    try {
                        if (bToBorrow == null) {
                            throw new BookNotFoundException("Error: Book ID '" + borrowBId + "' does not exist.");
                        }
                        if (mBorrower == null) {
                            System.out.println("Error: Member ID '" + borrowMId + "' not found.");
                            break;
                        }
                        library.borrowBook(bToBorrow, mBorrower);
                    } catch (BookNotFoundException | BookUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter Book ID to Return: ");
                    String returnBId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String returnMId = scanner.nextLine();

                    Book bToReturn = library.findBookById(returnBId);
                    Member mReturner = library.findMemberById(returnMId);

                    if (bToReturn == null) {
                        System.out.println("Error: Book ID not found.");
                    } else if (mReturner == null) {
                        System.out.println("Error: Member ID not found.");
                    } else {
                        library.returnBook(bToReturn, mReturner);
                    }
                    break;

                case 7:
                    library.saveBooksToFile(fileName);
                    break;

                case 8:
                    // Assignment requires saving automatically before the program closes
                    library.saveBooksToFile(fileName);
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
        scanner.close();
    }
}