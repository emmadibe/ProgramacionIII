package TpFinalProgramacionIII.Biblioteca.Enums;

public enum EstadoPrestamo
{
    ACTIVO("Activo", 1),
    DEVUELTO("Devuelto", 2);

    private int orden;
    private String name;

    private EstadoPrestamo(String name, int orden)
    {
        this.setName(name);
        this.setOrden(orden);
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
