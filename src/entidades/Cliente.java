package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Long Doc;
    private String nombre;
    private String apellido;
    private String telefono;

    public Cliente() {
    }

    public Cliente(Integer ID, Long Doc, String nombre, String apellido, String telefono) {
        this.ID = ID;
        this.Doc = Doc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Long getDoc() {
        return Doc;
    }

    public void setDoc(Long Doc) {
        this.Doc = Doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ID=" + ID + ", Doc=" + Doc + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono;
    }
}
