package ec.edu.insta.movilgc1.model.ofertas;

public class Oferta {

    private int id;
    private String cargo;
    private String descripcion;
    private String area_conocimiento;
    private String salario;
    private String jornada;
    private String requisitos_academicos;
    private String experiencia;
    private String ubicacion;
    private String fecha_inicio;
    private String fecha_fin;
    private String empresa;
    private String ciudad;
    private Boolean estado;

    public Oferta() {
    }

    public Oferta(int id, String cargo, String descripcion, String area_conocimiento, String salario, String jornada, String requisitos_academicos, String experiencia, String ubicacion, String fecha_inicio, String fecha_fin, String empresa, String ciudad, Boolean estado) {
        this.id = id;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.area_conocimiento = area_conocimiento;
        this.salario = salario;
        this.jornada = jornada;
        this.requisitos_academicos = requisitos_academicos;
        this.experiencia = experiencia;
        this.ubicacion = ubicacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArea_conocimiento() {
        return area_conocimiento;
    }

    public void setArea_conocimiento(String area_conocimiento) {
        this.area_conocimiento = area_conocimiento;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getRequisitos_academicos() {
        return requisitos_academicos;
    }

    public void setRequisitos_academicos(String requisitos_academicos) {
        this.requisitos_academicos = requisitos_academicos;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
