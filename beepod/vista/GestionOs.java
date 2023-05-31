package beepod.vista;

import beepod.controlador.Controlador;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GestionOs extends Application {
    private Controlador control = new Controlador();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeInterface();
    }

    private void initializeInterface() {
        Parent root = buildInterface();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.sizeToScene();
    }

    private BorderPane buildInterface() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setStyle("-fx-background-color: #2B2B2B;");

        HBox hbox = new HBox(10);  // Aquí es donde colocamos los botones uno al lado del otro
        hbox.getChildren().addAll(
                buildButton("Gestión Artículos", event -> startArticleManagement()),
                buildButton("Gestión Clientes", event -> startClientManagement()),
                buildButton("Gestión Pedidos", event -> startOrderManagement())
        );

        Button exitButton = buildButton("Salir", event -> primaryStage.close());
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(exitButton, new Insets(30));

        borderPane.setCenter(hbox);
        borderPane.setBottom(exitButton);

        return borderPane;
    }

    private Button buildButton(String label, EventHandler<ActionEvent> handler) {
        Button button = new Button(label);
        button.setOnAction(handler);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        return button;
    }

    private void startArticleManagement() {
        GestionArticulos gestionArticulos = new GestionArticulos(control);
        gestionArticulos.inicio(primaryStage, this);
    }

    private void startClientManagement() {
        GestionClientes gestionClientes = new GestionClientes(control);
        gestionClientes.inicio(primaryStage, this);
    }

    private void startOrderManagement() {
        GestionPedidos gestionPedidos = new GestionPedidos(control);
        gestionPedidos.inicio(primaryStage, this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
