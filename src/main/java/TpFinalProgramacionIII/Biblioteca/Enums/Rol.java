package TpFinalProgramacionIII.Biblioteca.Enums;

public enum Rol
{
    ADMIN("Administrador", 1),
    USER("Usuario", 2);

    private String name;
    private int orden;

    private Rol(String name, int orden)
    {
        this.setName(name);
        this.setOrden(orden);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
