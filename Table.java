import java.io.*;
import java.util.*;

/**
 * The Table class represents a tabular data structure loaded from a file.
 * It provides methods for managing and manipulating the table data.
 */
public class Table {
    private String tablename;   // Name of the file containing the table data
    private int numRows;        // Number of rows in the table
    private int numCols;        // Number of columns in the table
    private String[][] grid;    // 2D array representing the table data

    /**
     * Constructs a Table object with data loaded from the specified file.
     *
     * @param filename The name of the file containing the table data.
     */
    public Table(String filename) {
        tablename = filename;
        loadFromFile();
    }

    /**
     * Private method to load data from the file into the table.
     */
    private void loadFromFile() {
        try {
            Scanner theFile = new Scanner(new FileInputStream(new File(tablename)));
            while (theFile.hasNextLine()) {
                numRows++;
                String[] item = theFile.nextLine().split("\t", -1);
                if (item.length > numCols)
                    numCols = item.length;
            }
            theFile.close();

            grid = new String[numRows][numCols];

            Scanner scanner = new Scanner(new FileInputStream(new File(tablename)));
            int r = 0;
            while (scanner.hasNextLine()) {
                String[] items = scanner.nextLine().split("\t", -1);
                for (int c = 0; c < numCols; c++) {
                    if (c < items.length)
                        grid[r][c] = items[c];
                    else
                        grid[r][c] = "";
                }
                r++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Table class Error: file not found.");
        }
    }

    /**
     * Checks if a ticket with the specified barcode is valid.
     *
     * @param barcode The barcode of the ticket to check.
     * @return True if the ticket is valid, false otherwise.
     */
    public boolean isTicketValid(String barcode) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return true;
        }
        return false;
    }

    /**
     * Checks if a ticket with the specified barcode has been entered.
     *
     * @param barcode The barcode of the ticket to check.
     * @return True if the ticket has been entered, false otherwise.
     */
    public boolean isTicketEntered(String barcode) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return grid[r][2].equals("N");
        }
        return true;
    }

    /**
     * Marks a ticket with the specified barcode as entered.
     *
     * @param barcode The barcode of the ticket to mark as entered.
     */
    public void markTicketEntered(String barcode) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode)) {
                grid[r][2] = "Y";
                break;
            }
        }
        updateFile();
    }

    /**
     * Resets all ticket entries to "N", indicating they have not been entered.
     */
    public void resetTicketEntries() {
        for (int r = 0; r < numRows; r++) {
            grid[r][2] = "N";
        }
        updateFile();
    }

    /**
     * Updates the file with the modified table data.
     */
    public void updateFile() {
        try (FileWriter writer = new FileWriter(new File(tablename))) {
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    writer.write(grid[r][c]);
                    if (c < numCols - 1)
                        writer.write("\t");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating file.");
        }
    }

    /**
     * Displays the table data.
     */
    public void display() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                System.out.print(grid[r][c] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Looks up a row in the table based on a key value.
     *
     * @param key The key value to search for.
     * @return The index of the row containing the key, or -1 if not found.
     */
    public int lookup(String key) {
        for (int r = 0; r < numRows; r++) {
            if (key.equals(grid[r][0]))
                return r;
        }
        return -1;
    }

    /**
     * Retrieves the data for a row with the specified key.
     *
     * @param key The key value to search for.
     * @return An array containing the data for the row, or an empty array if the key is not found.
     */
    public String[] getMatches(String key) {
        String[] str = new String[numCols];
        int rowNum = lookup(key);
        if (rowNum == -1)
            return str;
        for (int c = 0; c < numCols; c++)
            str[c] = grid[rowNum][c];
        return str;
    }

    /**
     * Gets the number of columns in the table.
     *
     * @return The number of columns in the table.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Gets the value of a single cell in the table.
     *
     * @param row   The row index of the cell.
     * @param col   The column index of the cell.
     * @return The value of the cell, or an empty string if the indices are out of bounds.
     */
    public String getSingleCellValue(int row, int col) {
        if (row >= 0 && row < numRows)
            if (col >= 0 && col < numCols)
                return grid[row][col];
        return "";
    }

    /**
     * Sets the value of a single cell in the table.
     *
     * @param row   The row index of the cell.
     * @param col   The column index of the cell.
     * @param value The new value to set.
     */
    public void setSingleCellValue(int row, int col, String value) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols)
            grid[row][col] = value;
    }

    /**
     * Checks if a ticket with the specified barcode has been purchased.
     *
     * @param barcode The barcode of the ticket to check.
     * @return True if the ticket has been purchased, false otherwise.
     */
    public boolean isTicketPurchased(String barcode) {
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return grid[r][1].equals("Y");
        }
        return false;
    }

    /**
     * Purchases all tickets by setting their purchase status to "Y".
     */
    public void PurchaseTicket() {
        for (int r = 0; r < numRows; r++) {
            grid[r][1] = "Y";
        }
        updateFile();
    }
}
