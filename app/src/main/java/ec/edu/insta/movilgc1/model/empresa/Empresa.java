package ec.edu.insta.movilgc1.model.empresa;
public class Empresa {
    private int id;
    private String sectorEmpresarial;
    private String ruc;
    private String nombre;
    private String tipoEmpresa;
    private String razonSocial;
    private String departamento;
    private String ciudad;
    private String direccion;
    private String sitioWeb;
    private Boolean estado;
    public Empresa() {
    }
    public Empresa(int id, String sectorEmpresarial, String ruc, String nombre, String tipoEmpresa, String razonSocial, String departamento, String ciudad, String direccion, String sitioWeb, Boolean estado) {
        this.id = id;
        this.sectorEmpresarial = sectorEmpresarial;
        this.ruc = ruc;
        this.nombre = nombre;
        this.tipoEmpresa = tipoEmpresa;
        this.razonSocial = razonSocial;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.estado = estado;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSectorEmpresarial() {
        return sectorEmpresarial;
    }
    public void setSectorEmpresarial(String sectorEmpresarial) {
        this.sectorEmpresarial = sectorEmpresarial;
    }
    public String getRuc() {
        return ruc;
    }
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }
    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getSitioWeb() {
        return sitioWeb;
    }
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
