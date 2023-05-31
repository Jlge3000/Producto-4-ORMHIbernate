package beepod.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idPedido")
    private int numPedido;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name= "email")
    private Cliente cliente;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name= "codigo")
    private Articulo articulo;
    @Column(name= "cantidad")
    private int cantidad;
    @Column(name= "fecha")
    private LocalDateTime fecha;
    @Column(name= "enviado")
    private boolean enviado;
    @Column(name= "total")
    private float total;

    /*
    Constructores
     * */

    public Pedido() {
    }

    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.cliente= cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }
    /*
     * Getters y Setters
     * */
    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public String getEmailCliente() {
        return this.cliente.getEmail();
    }

    public String getCodigoArticulo() {
        return this.articulo.getCodigo();
    }

    /*
     * toString*/
    @Override
    public String toString() {
        return "Pedido{\n" +
                "ID Pedido: " + numPedido +
                "\nCliente: " + cliente.getEmail() +
                "\nArticulo: " + articulo.getDescripcion() +
                " Cantidad: " + cantidad +
                "\nTotal: " + total + "€" +
                " Fecha: " + fecha +
                " Enviado: " + enviado +
                "}\n";
    }
}