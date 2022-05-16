import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static List<Book> books = new ArrayList<>();
    public record Book(String name, int count, int cost){ }
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        String input;
        String start_input;
        String balance_text;
        int i = 0;
        int y = 0;
        String exit = "exit";
        String current_balance = "print balance";
        String stock = "show books in stock";
        String bought = "show bought books";
        String buy_book = "buy book";
        start_input = scanner.nextLine();
        balance_text = start_input;
        int balance = Integer.parseInt(balance_text.split(",")[0].replace("balance: ", ""));
        start_input = start_input.replace("[", "")
                .replace("]", "")
                .replace("(", "")
                .replaceAll("(balance: [0-9]+, books:)", "")
                .trim();

        for(String book : start_input.split("(\\), )"))
        {
            String book_name = book.split("\",")[0]
                    .replaceAll("\"", "")
                    .trim();
            int count = Integer.parseInt(book.split("\",")[1]
                    .split(",")[0]
                    .trim());
            int cost = Integer.parseInt(book.split("\",")[1]
                    .split((","))[1]
                    .replaceAll("\\)", "")
                    .trim());// ...

            books.add(new Book(book_name, count, cost));
        }

        while(true)
        {
            System.out.println();
            input = scanner.nextLine();
            if (input.length() >= 9)
            {
                String buy_cmd = input.substring(0, 9);
                if (buy_cmd.equals(buy_book))
            {
                String[] parts = input.split("\"");
                String BK_Name = parts[1];
                String Buy_Count = parts[2].trim();

                int BuyU_Count = Integer.parseInt(Buy_Count);
                Book a = books.stream()
                        .filter(book-> BK_Name.equals(book.name()))
                        .findAny()
                        .orElse(null);

                if (a != null)
                {
                    books.remove(a);
                    Book newBook = new Book(a.name(), a.count()-BuyU_Count, a.cost());
                    if (newBook.count() <= 0)
                        books.add(newBook);
                }
            }
            }

            if (input.equals(exit))
            {
                break;
            }
            else if (input.equals(current_balance))
            {
                System.out.println("balance: "+balance+" руб");

            }
            else if (input.equals(stock))
            {
                for (Book book : books) {
                    System.out.printf("%s, %d шт, %d руб %n", book.name(), book.count(), book.cost());
                }
            }
            else if (input.equals(bought))
            {

            }
        }
    }
}


