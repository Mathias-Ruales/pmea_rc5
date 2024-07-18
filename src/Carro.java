import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Carro {
    private String nombre_dueno;
    private String placa;
    private double precio;
    private double precioDescuento;
    private LocalDate fecha;
    public Carro(String nombre_dueno, String placa, double precio) {
        this.nombre_dueno = nombre_dueno;
        this.placa = placa;
        this.fecha = LocalDate.now();
        this.precio = precio;
    }

    public String getNombre_dueno() {
        return nombre_dueno;
    }

    public void setNombre_dueno(String nombre_dueno) {
        this.nombre_dueno = nombre_dueno;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(double precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    boolean descuentoProntoPago() {
        int opcionDescuento = JOptionPane.showConfirmDialog(null,
                "El carro aplica para pronto pago?",
                "Seleccione una opción",
                JOptionPane.YES_NO_OPTION);
        return opcionDescuento == JOptionPane.YES_OPTION;
    }
    boolean descuentoServicio() {
        int opcionDescuento = JOptionPane.showConfirmDialog(null,
                "El carro es de servicio público?",
                "Seleccione una opción",
                JOptionPane.YES_NO_OPTION);
        return opcionDescuento == JOptionPane.YES_OPTION;
    }

    boolean descuentoTrasladoCuenta (){
        int opcionDescuento = JOptionPane.showConfirmDialog(null,
                "El carro esta siendo trasladado de dueño?",
                "Seleccione una opción",
                JOptionPane.YES_NO_OPTION);
        return opcionDescuento == JOptionPane.YES_OPTION;
    }

    public String calcularDescuento(Carro carro) {
        double precioOriginal = carro.getPrecio();
        double precioConDescuento = precioOriginal;
        StringBuilder descuentosAplicados = new StringBuilder();

        if (descuentoProntoPago()) {
            precioConDescuento *= 0.75;
            descuentosAplicados.append("Pronto Pago (25%)\n");
        }

        if (descuentoServicio()) {
            precioConDescuento *= 0.90;
            descuentosAplicados.append("Servicio Público (10%)\n");
        }

        if (descuentoTrasladoCuenta()) {
            precioConDescuento *= 0.95;
            descuentosAplicados.append("Traslado de Cuenta (5%)\n");
        }

        carro.setPrecioDescuento(precioConDescuento);
        double precioConImpuestos = precioConDescuento * 1.14;

        return "Precio original: " + precioOriginal + "\n" +
                "Descuentos aplicados:\n" + descuentosAplicados +
                "Precio con descuentos: " + precioConDescuento + "\n" +
                "Precio con impuestos (14%): " + precioConImpuestos;
    }

}
