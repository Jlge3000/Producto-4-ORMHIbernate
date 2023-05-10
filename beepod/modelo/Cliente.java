package beepod.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
    /*
     * Atributos
     **/
    @Column(name= "nombre")
    private String nombre;

    @Column(name= "domicilio")
    private String domicilio;

    @Column(name= "nif")
    private String nif;

    @Id
    @Column(name= "email")
    private String email;

    @Column(name= "tipoCliente")
    private String tipoCliente;
    @Column(name= "cuota")
    private float cuota;
    @Column(name= "descuento")
    private float descuento;

    public Cliente(String nombre, String domicilio, String nif, String email, String tipoCliente, float cuota, float descuento) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.cuota = cuota;
        this.descuento = descuento;
    }

    public Cliente(String nombre, String domicilio, String nif, String email, String tipoCliente) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre +", domicilio= " + domicilio +", nif= " + nif + ", email= " + email +", tipoCliente= " + tipoCliente +", cuota=" + cuota +", descuento=" + descuento * 100 + "%" ;
    }
}
