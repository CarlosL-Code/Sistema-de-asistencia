package domain;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String pass;
    private String tipoUsuario;

    public Usuario(int id, String nombre, String email, String pass, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", email='" + email + '\''
                + ", tipoUsuario='" + tipoUsuario + '\''
                + '}';
    }

}
