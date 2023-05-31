package beepod.vista;

import beepod.controlador.Controlador;
import beepod.modelo.Pedido;
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
import java.util.List;

public class GestionPedidos extends Application {
    public Controlador control;
    private TableView<Pedido> tableView = new TableView<>();

    public GestionPedidos(Controlador control) {
        this.control = control;
    }

    public void inicio(Stage primaryStage, GestionOs gestionOs) {
        primaryStage.setTitle("Gestión de Pedidos");
        VBox vboxMain = new VBox();
        vboxMain.setStyle("-fx-background-color: #2B2B2B;");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #2B2B2B;");

        Label emailLabel = new Label("Email: ");
        emailLabel.setTextFill(Color.WHITE);
        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Text codigoLabel = new Text("Código del producto: ");
        codigoLabel.setFill(Color.WHITE);
        TextField codigoField = new TextField();
        codigoField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Text cantidadLabel = new Text("Cantidad: ");
        cantidadLabel.setFill(Color.WHITE);
        TextField cantidadField = new TextField();
        cantidadField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Text pedidoIdLabel = new Text("Pedido ID: ");
        pedidoIdLabel.setFill(Color.WHITE);
        TextField pedidoIdField = new TextField();
        pedidoIdField.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");

        Button btnAddPedido = new Button("Añadir Pedido");
        btnAddPedido.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAddPedido.setOnAction(e -> {
            try {
                String email = emailField.getText();
                String codigo = codigoField.getText();
                int cantidad = Integer.parseInt(cantidadField.getText());
                control.crearPedido(email, codigo, cantidad);

                List<Pedido> listaPedidos = control.filtrarPedidosPendientes();
                mostrarPedidosEnTableView(tableView, listaPedidos);

                emailField.clear();
                codigoField.clear();
                cantidadField.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnEliminarPedido = new Button("Eliminar Pedido");
        btnEliminarPedido.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnEliminarPedido.setOnAction(e -> {
            try {
                int pedidoId = Integer.parseInt(pedidoIdField.getText());
                if (control.eliminarPedido(pedidoId)) {
                    List<Pedido> listaPedidos = control.filtrarPedidosPendientes();
                    mostrarPedidosEnTableView(tableView, listaPedidos);

                    pedidoIdField.clear();
                } else {
                    System.out.println("No se pudo eliminar el pedido.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Número de pedido inválido.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnMostrarPedidosPendientes = new Button("Mostrar Pedidos Pendientes");
        btnMostrarPedidosPendientes.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnMostrarPedidosPendientes.setOnAction(e -> {
            try {
                List<Pedido> listaPedidos = control.filtrarPedidosPendientes();

                System.out.println("Pedidos pendientes: " + listaPedidos);

                mostrarPedidosEnTableView(tableView, listaPedidos);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnMostrarPedidosEnviados = new Button("Mostrar Pedidos Enviados");
        btnMostrarPedidosEnviados.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnMostrarPedidosEnviados.setOnAction(e -> {
            try {
                List<Pedido> listaPedidos = control.filtrarPedidosEnviados();
                mostrarPedidosEnTableView(tableView, listaPedidos);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnMostrarPedidosPendientesCliente = new Button("Mostrar Pedidos Pendientes de Cliente");
        btnMostrarPedidosPendientesCliente.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnMostrarPedidosPendientesCliente.setOnAction(e -> {
            try {
                String email = emailField.getText();
                List<Pedido> listaPedidos = control.filtrarPedidosPendientesPorNombreCliente(email);
                mostrarPedidosEnTableView(tableView, listaPedidos);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnMostrarPedidosEnviadosCliente = new Button("Mostrar Pedidos Enviados de Cliente");
        btnMostrarPedidosEnviadosCliente.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnMostrarPedidosEnviadosCliente.setOnAction(e -> {
            try {
                String email = emailField.getText();
                List<Pedido> listaPedidos = control.filtrarPedidosEnviadosPorNombreCliente(email);
                mostrarPedidosEnTableView(tableView, listaPedidos);
            } catch (Exception ex) {
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
        Text emptyMessage = new Text("Tabla sin contenido");
        emptyMessage.setFill(Color.WHITE);
        tableView.setPlaceholder(emptyMessage);

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(codigoLabel, 0, 1);
        gridPane.add(codigoField, 1, 1);
        gridPane.add(cantidadLabel, 0, 2);
        gridPane.add(cantidadField, 1, 2);
        gridPane.add(pedidoIdLabel, 0, 3);
        gridPane.add(pedidoIdField, 1, 3);
        gridPane.add(btnAddPedido, 0, 4);
        gridPane.add(btnEliminarPedido, 1, 4);
        gridPane.add(btnMostrarPedidosPendientes, 0, 5);
        gridPane.add(btnMostrarPedidosEnviados, 1, 5);
        gridPane.add(btnMostrarPedidosPendientesCliente, 0, 6);
        gridPane.add(btnMostrarPedidosEnviadosCliente, 1, 6);

        VBox vboxMainForInicio = new VBox();
        vboxMainForInicio.setStyle("-fx-background-color: #2B2B2B;");
        vboxMainForInicio.getChildren().addAll(gridPane, tableView, volverButton);

        Scene sceneInicio = new Scene(vboxMainForInicio, 600, 400);
        sceneInicio.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(sceneInicio);
        primaryStage.setTitle("Gestión de Pedidos");
        primaryStage.show();
    }

    public void mostrarPedidosEnTableView(TableView<Pedido> tableView, List<Pedido> listaPedidos) {
        TableColumn<Pedido, String> emailColumn = new TableColumn<>("Email Cliente");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));

        TableColumn<Pedido, Integer> idColumn = new TableColumn<>("ID Pedido");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("numPedido")); // Cambia 'idPedido' por 'numPedido'

        TableColumn<Pedido, String> codigoColumn = new TableColumn<>("Codigo Producto");
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoArticulo"));

        TableColumn<Pedido, Integer> cantidadColumn = new TableColumn<>("Cantidad");
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tableView.getColumns().setAll(idColumn, emailColumn, codigoColumn, cantidadColumn);

        ObservableList<Pedido> observableList = FXCollections.observableArrayList(listaPedidos);
        tableView.setItems(observableList);
    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}
