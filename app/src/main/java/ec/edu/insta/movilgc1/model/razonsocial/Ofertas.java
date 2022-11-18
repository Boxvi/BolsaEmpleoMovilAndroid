package ec.edu.insta.movilgc1.model.razonsocial;

public class Ofertas {

    public int id;
    public String empresa;
    public String salario;
    public String cargo;
    public String ciudad;

    public Ofertas() {
    }

    public Ofertas(int id, String empresa, String salario, String cargo, String ciudad) {
        this.id = id;
        this.empresa = empresa;
        this.salario = salario;
        this.cargo = cargo;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

