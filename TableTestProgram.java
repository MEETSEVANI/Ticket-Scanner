import java.util.Scanner;

/**
 * The class TableTestProgram is a test program for the Table class.
 * It provides a command-line interface to interact with the table data.
 */
public class TableTestProgram {

    /**
     * Main method to run the table test program.
     *
     * @param args Command-line arguments (not used)
     * @throws Exception If an error occurs during file input/output or user input
     */
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        String tablename = "";

        // Prompt user to enter the name of the tab-delimited text file to manage
        System.out.print("Enter the name of the tab delimited text file you wish to manage (e.g. codes.txt) > ");
        tablename = in.nextLine();
        Table t = new Table(tablename);
        System.out.println("Successfully loaded: " + tablename);

        // Main loop to display menu and handle user input
        while (true) {
            System.out.println("\n\nTable Testing Menu\n");
            System.out.println("1. Display all data");
            System.out.println("2. Lookup");
            System.out.println("3. Search");
            System.out.println("4. Change");
            System.out.println("5. Save data to " + tablename);
            System.out.println("6. Get Single Cell Value");
            System.out.println("7. Save Single Cell Value");
            System.out.println("8. Purchase Ticket");
            System.out.println("9. Quit");
            System.out.print("Select > ");
            String choice = in.nextLine();

            if (choice.equals("9")) break;

            switch (choice) {
                case "1":
                    t.display();
                    break;
                case "2":
                    // Lookup operation
                    System.out.print("Enter the Primary key > ");
                    String target = in.nextLine();
                    int rowNum = t.lookup(target);
                    if (rowNum == -1)
                        System.out.print(target + " not Found. ");
                    else
                        System.out.print(target + " found at the row number " + rowNum);
                    break;
                case "3":
                    // Search operation
                    System.out.print("Enter the Primary key > ");
                    target = in.nextLine();
                    int rowNumSearch = t.lookup(target);
                    if (rowNumSearch == -1)
                        System.out.println(target + " not Found. ");
                    else {
                        String[] str = t.getMatches(target);
                        for (int s = 0; s < str.length; s++) {
                            System.out.printf("[%d] - %s\n", s, str[s]);
                        }
                    }
                    break;
                case "4":
                    // Change operation
                    System.out.print("Enter Row Number: ");
                    int rowNo = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Column Number: ");
                    int colNo = in.nextInt();
                    in.nextLine();
                    String targetCellValue = t.getSingleCellValue(rowNo, colNo);
                    System.out.println("The cell Value is : " + targetCellValue);
                    System.out.println("Please Enter a New Value: ");
                    String newValue = in.nextLine();
                    t.setSingleCellValue(rowNo, colNo, newValue); // Update the cell value
                    System.out.println("New value set successfully!");
                    break;
                case "5":
                    // Save data to file
                    t.updateFile();
                    System.out.println("Data saved to " + tablename);
                    break;
                case "6":
                    // Get Single Cell Value operation
                    System.out.print("Enter Row Number: ");
                    int singleRowNo = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Column Number: ");
                    int singleColNo = in.nextInt();
                    in.nextLine();
                    String singleCellValue = t.getSingleCellValue(singleRowNo, singleColNo);
                    System.out.println("The cell Value is : " + singleCellValue);
                    break;
                case "7":
                    // Save Single Cell Value operation
                    System.out.print("Enter Row Number: ");
                    int rowForSave = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Column Number: ");
                    int colForSave = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter New Value: ");
                    String valueForSave = in.nextLine();
                    t.setSingleCellValue(rowForSave, colForSave, valueForSave); // Update the cell value
                    System.out.println("Single cell value saved successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }
        System.out.println("Thank You Visit Again");
    }
}
