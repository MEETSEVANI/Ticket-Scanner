import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The class Ticket scanning application extends application
 */
public class TicketScanningApp extends Application {

    private TextField barcodeInput;
    private Label statusLabel;
    private Table table;

    @Override
    public void start(Stage stage) throws Exception {

        // Create the table object to manage ticket data
        table = new Table("codes.txt");

        // Root layout
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f4f4f4;");

        // Title section
        Label title = new Label("Ticket Scanner App");
        title.setFont(new Font("Arial Bold", 24));
        title.setTextFill(Color.DARKBLUE);

        HBox titleBox = new HBox(title);
        titleBox.setPadding(new Insets(10));
        titleBox.setStyle("-fx-border-color: #dcdcdc; -fx-border-width: 0 0 2 0; -fx-padding: 10;");

        // Barcode input section
        GridPane inputPane = new GridPane();
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        inputPane.setPadding(new Insets(10, 0, 10, 0));

        Label barcodeLabel = new Label("Enter Ticket Number:");
        barcodeLabel.setFont(new Font("Arial", 14));
        barcodeInput = new TextField();
        barcodeInput.setPromptText("Ticket Number");
        barcodeInput.setPrefWidth(300);

        inputPane.add(barcodeLabel, 0, 0);
        inputPane.add(barcodeInput, 1, 0);

        // Buttons section
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(15);

        Button scanButton = new Button("Scan");
        scanButton.setPrefWidth(100);
        scanButton.setOnAction(this::handleScanButton);

        Button resetButton = new Button("Reset");
        resetButton.setPrefWidth(100);
        resetButton.setOnAction(this::handleResetButton);

        Button purchaseButton = new Button("Purchase");
        purchaseButton.setPrefWidth(100);
        purchaseButton.setOnAction(this::handlePurchaseButton);

        buttonBox.getChildren().addAll(scanButton, resetButton, purchaseButton);

        // Status label section
        statusLabel = new Label("Ready to scan.");
        statusLabel.setFont(new Font("Arial", 14));
        statusLabel.setTextFill(Color.GRAY);

        // Add all sections to the root
        root.getChildren().addAll(titleBox, inputPane, buttonBox, statusLabel);

        // Setup stage and scene
        Scene scene = new Scene(root, 500, 250);
        stage.setTitle("Ticket Gate App");
        stage.setScene(scene);
        stage.show();
    }

    private void handleScanButton(ActionEvent event) {
        String barcode = barcodeInput.getText().trim().toUpperCase();
        if (barcode.isEmpty()) {
            statusLabel.setText("Please enter a ticket number.");
            statusLabel.setTextFill(Color.ORANGE);
            return;
        }

        if (table.isTicketPurchased(barcode)) {
            if (table.isTicketValid(barcode)) {
                if (!table.isTicketEntered(barcode)) {
                    table.markTicketEntered(barcode);
                    statusLabel.setText("Access granted for ticket: " + barcode);
                    statusLabel.setTextFill(Color.GREEN);
                } else {
                    statusLabel.setText("Duplicate ticket: " + barcode);
                    statusLabel.setTextFill(Color.RED);
                }
            } else {
                statusLabel.setText("Invalid ticket: " + barcode);
                statusLabel.setTextFill(Color.RED);
            }
        } else {
            statusLabel.setText("Ticket not purchased: " + barcode);
            statusLabel.setTextFill(Color.RED);
        }

        barcodeInput.clear();
    }

    private void handleResetButton(ActionEvent event) {
        table.resetTicketEntries();
        statusLabel.setText("All tickets have been reset.");
        statusLabel.setTextFill(Color.GRAY);
    }

    private void handlePurchaseButton(ActionEvent event) {
        String barcode = barcodeInput.getText().trim().toUpperCase();
        if (barcode.isEmpty()) {
            statusLabel.setText("Please enter a ticket number.");
            statusLabel.setTextFill(Color.ORANGE);
            return;
        }
        table.PurchaseTicket();
        statusLabel.setText("Ticket purchased successfully.");
        statusLabel.setTextFill(Color.GREEN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
