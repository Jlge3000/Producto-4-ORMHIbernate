package beepod.vista;

import beepod.controlador.Controlador;
import beepod.modelo.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestionClientes extends Application {
    public Controlador control;
    private TableView<Cliente> tableView = new TableView<>();

    public GestionClientes(Controlador control) {
        this.control = control;
    }

    public void inicio(Stage primaryStage, GestionOs gestionOs) {
        primaryStage.setTitle("Gestión de Clientes");
        VBox vboxMain = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #2B2B2B;");

        Text emptyMessage = new Text("Tabla sin contenido");
        emptyMessage.setFill(Color.WHITE);

        tableView.setPlaceholder(emptyMessage);

        Label nombreLabel = new Label("Nombre: ");
        nombreLabel.setTextFill(Color.WHITE);
        TextField nombreField = new TextField();
        nombreField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Label domicilioLabel = new Label("Domicilio: ");
        domicilioLabel.setTextFill(Color.WHITE);
        TextField domicilioField = new TextField();
        domicilioField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Label nifLabel = new Label("NIF: ");
        nifLabel.setTextFill(Color.WHITE);
        TextField nifField = new TextField();
        nifField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Label emailLabel = new Label("Email: ");
        emailLabel.setTextFill(Color.WHITE);
        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Label tipoLabel = new Label("Tipo: ");
        tipoLabel.setTextFill(Color.WHITE);
        ComboBox<String> tipoComboBox = new ComboBox<>();
        tipoComboBox.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
        tipoComboBox.getItems().addAll("Estandar", "Premium");

        Button button1 = new Button("Añadir Clientes");
        button1.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button1.setOnAction(e -> {
            try {
                String nombre = nombreField.getText();
                String domicilio = domicilioField.getText();
                String nif = nifField.getText();
                String email = emailField.getText();
                String tipo = tipoComboBox.getValue();
                int tipoValue = tipo.equals("Estandar") ? 1 : 2;
                control.crearCliente(nombre, domicilio, nif, email, tipoValue);

                List<Cliente> listaClientes = control.listarTodosClientes();
                mostrarClientesEnTableView(tableView, listaClientes);

                nombreField.clear();
                domicilioField.clear();
                nifField.clear();
                emailField.clear();
                tipoComboBox.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Button button2 = new Button("Ver Clientes");
        button2.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button2.setOnAction(e -> {
            try {
                List<Cliente> listaClientes = control.listarTodosClientes();
                mostrarClientesEnTableView(tableView, listaClientes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button button3 = new Button("Ver Clientes Normales");
        button3.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button3.setOnAction(e -> {
            try {
                List<Cliente> listaClientes = control.listarClientesNormal();
                mostrarClientesEnTableView(tableView, listaClientes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button button4 = new Button("Ver Clientes Premium");
        button4.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button4.setOnAction(e -> {
            try {
                List<Cliente> listaClientes = control.listarClientesPremium();
                mostrarClientesEnTableView(tableView, listaClientes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button volverButton = new Button("Volver");
        volverButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        volverButton.setOnAction(e -> {
            try {
                gestionOs.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        tableView.setMaxHeight(150);

        gridPane.add(nombreLabel, 0, 0);
        gridPane.add(nombreField, 1, 0);
        gridPane.add(domicilioLabel, 0, 1);
        gridPane.add(domicilioField, 1, 1);
        gridPane.add(nifLabel, 0, 2);
        gridPane.add(nifField, 1, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailField, 1, 3);
        gridPane.add(tipoLabel, 0, 4);
        gridPane.add(tipoComboBox, 1, 4);
        gridPane.add(button1, 0, 5);
        gridPane.add(button2, 1, 5);
        gridPane.add(button3, 0, 6);
        gridPane.add(button4, 1, 6);

        vboxMain.getChildren().addAll(gridPane, tableView, volverButton);

        Scene scene = new Scene(vboxMain, 600, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // Agregar archivo CSS para estilo oscuro

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mostrarClientesEnTableView(TableView<Cliente> tableView, List<Cliente> listaClientes) {
        TableColumn<Cliente, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Cliente, String> domicilioColumn = new TableColumn<>("Domicilio");
        domicilioColumn.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

        TableColumn<Cliente, String> nifColumn = new TableColumn<>("NIF");
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));

        TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cliente, String> tipoColumn = new TableColumn<>("Tipo");
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));

        TableColumn<Cliente, Float> cuotaColumn = new TableColumn<>("Cuota");
        cuotaColumn.setCellValueFactory(new PropertyValueFactory<>("cuota"));

        TableColumn<Cliente, Float> descuentoColumn = new TableColumn<>("Descuento");
        descuentoColumn.setCellValueFactory(new PropertyValueFactory<>("descuento"));

        tableView.getColumns().setAll(nombreColumn, domicilioColumn, nifColumn, emailColumn, tipoColumn, cuotaColumn, descuentoColumn);

        ObservableList<Cliente> observableList = FXCollections.observableArrayList(listaClientes);
        tableView.setItems(observableList);
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
