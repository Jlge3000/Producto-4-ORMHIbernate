package beepod.vista;

import beepod.controlador.Controlador;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import beepod.modelo.Articulo;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.List;

public class GestionArticulos extends Application {
    private Controlador control;
    private TableView<Articulo> tableView = new TableView<>();

    public GestionArticulos(Controlador control) {
        this.control = control;
    }

    public void inicio(Stage primaryStage, GestionOs gestionOs) {
        primaryStage.setTitle("Gestión de Artículos");

        VBox vboxMain = new VBox();
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #2B2B2B;");

        tableView = new TableView<>();
        Text emptyMessage = new Text("Tabla sin contenido");
        emptyMessage.setFill(Color.WHITE);
        tableView.setPlaceholder(emptyMessage);

        Label codigoLabel = new Label("Codigo: ");
        codigoLabel.setTextFill(Color.WHITE);
        TextField codigoField = new TextField();
        codigoField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
        Label descripcionLabel = new Label("Descripcion: ");
        descripcionLabel.setTextFill(Color.WHITE);
        TextField descripcionField = new TextField();
        descripcionField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
        Label precioVentaLabel = new Label("Precio de venta: ");
        precioVentaLabel.setTextFill(Color.WHITE);
        TextField precioVentaField = new TextField();
        precioVentaField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
        Label gastosEnvioLabel = new Label("Gastos de envio: ");
        gastosEnvioLabel.setTextFill(Color.WHITE);
        TextField gastosEnvioField = new TextField();
        gastosEnvioField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
        Label tiempoPreparacionLabel = new Label("Tiempo de preparacion: ");
        tiempoPreparacionLabel.setTextFill(Color.WHITE);
        TextField tiempoPreparacionField = new TextField();
        tiempoPreparacionField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Button btnAddArticulo = new Button("Añadir Articulo");
        btnAddArticulo.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAddArticulo.setOnAction(event -> {
            try {
                control.crearArticulo(codigoField.getText(), descripcionField.getText(), Float.parseFloat(precioVentaField.getText()),
                        Float.parseFloat(gastosEnvioField.getText()), Long.parseLong(tiempoPreparacionField.getText()));
                List<Articulo> articulos = control.listarArticulos();
                mostrarArticulosEnTableView(tableView, articulos);
                codigoField.clear();
                descripcionField.clear();
                precioVentaField.clear();
                gastosEnvioField.clear();
                tiempoPreparacionField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Button btnMostrarArticulos = new Button("Mostrar Articulos");
        btnMostrarArticulos.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnMostrarArticulos.setOnAction(event -> {
            try {
                List<Articulo> articulos = control.listarArticulos();
                mostrarArticulosEnTableView(tableView, articulos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gridPane.add(codigoLabel, 0, 0);
        gridPane.add(codigoField, 1, 0);
        gridPane.add(descripcionLabel, 0, 1);
        gridPane.add(descripcionField, 1, 1);
        gridPane.add(precioVentaLabel, 0, 2);
        gridPane.add(precioVentaField, 1, 2);
        gridPane.add(gastosEnvioLabel, 0, 3);
        gridPane.add(gastosEnvioField, 1, 3);
        gridPane.add(tiempoPreparacionLabel, 0, 4);
        gridPane.add(tiempoPreparacionField, 1, 4);
        gridPane.add(btnAddArticulo, 0, 5);
        gridPane.add(btnMostrarArticulos, 1, 5);

        vboxMain.getChildren().addAll(gridPane, tableView);

        Scene scene = new Scene(vboxMain, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion Articulos");
        primaryStage.show();

        Button volverButton = new Button("Volver");
        volverButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        volverButton.setOnAction(e -> {
            try {
                gestionOs.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vboxMainForInicio = new VBox();
        vboxMainForInicio.setStyle("-fx-background-color: #2B2B2B;");
        vboxMainForInicio.getChildren().addAll(gridPane, tableView, volverButton);

        Scene sceneInicio = new Scene(vboxMainForInicio, 600, 400);
        sceneInicio.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(sceneInicio);
        primaryStage.setTitle("Gestion Articulos");
        primaryStage.show();
    }

    public void mostrarArticulosEnTableView(TableView<Articulo> tableView, List<Articulo> articulos) {
        tableView.getColumns().clear();

        TableColumn<Articulo, String> codigoColumn = new TableColumn<>("Codigo");
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Articulo, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Articulo, Float> precioColumn = new TableColumn<>("Precio de venta");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        TableColumn<Articulo, Float> gastosColumn = new TableColumn<>("Gastos de envio");
        gastosColumn.setCellValueFactory(new PropertyValueFactory<>("gastosEnvio"));

        TableColumn<Articulo, Long> tiempoColumn = new TableColumn<>("Tiempo de preparacion");
        tiempoColumn.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacion"));

        tableView.getColumns().addAll(codigoColumn, descripcionColumn, precioColumn, gastosColumn, tiempoColumn);

        ObservableList<Articulo> observableList = FXCollections.observableArrayList(articulos);
        tableView.setItems(observableList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        inicio(stage, null);
    }
}
