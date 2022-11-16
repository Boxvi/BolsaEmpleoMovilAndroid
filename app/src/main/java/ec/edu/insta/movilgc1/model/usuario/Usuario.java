package ec.edu.insta.movilgc1.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//@Data
public class Usuario {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private boolean estado;
    private String fechaCreacion;
    private String rol;

    public Usuario() {
    }

    public Usuario(Integer id, String username, String password, String email, String telefono, boolean estado, String fechaCreacion, String rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
