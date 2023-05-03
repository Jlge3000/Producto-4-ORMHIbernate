package beepod.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="articulos")
public class Articulo {
    /*
     * Variables
     * */
    @Id
    @Column(name= "codigo")
    private String codigo;
    @Column(name= "descripcion")
    private String descripcion;
    @Column(name= "pvp")
    private float precioVenta;
    @Column(name= "envio")
    private float gastosEnvio;
    @Column(name= "tiempoPreparacion")
    private long tiempoPreparacion;
    /*
     * Constructores
     * */

    public Articulo() {
    }

    public Articulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }
    /*
    Setters y getters
     */

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public long getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(long tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
    /*
    toString()
    * */
    @Override
    public String toString() {
        return "Articulo Codigo: " + codigo +" Descripcion: " + descripcion + " PVP: " + precioVenta + "€" +" Envio: " + gastosEnvio + "€" + " Preparacion: " + tiempoPreparacion +" Min.";
    }
}

