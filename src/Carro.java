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

    int descuentoProntoPago() {
        LocalDate fechaPago = fecha.plusMonths(1);
        String message = "Fecha actual: " + fecha + "\n" +
                "Fecha de pago: " + fechaPago + "\n" +
                "Con cuantos dias de anticipacion desea pagar?";
        String[] options = {"30 dias", "15 dias", "El mismo dia"};
        int opcion = JOptionPane.showOptionDialog(null,
                message,
                "Seleccione una opción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        return switch (opcion) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + opcion);
        };
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
        int dias = descuentoProntoPago();
        switch (dias){
            case 0:
                precioConDescuento *= 0.95;
                descuentosAplicados.append("Pronto Pago 30 días (5%)\n");
                break;
            case 1:
                precioConDescuento *= 0.85;
                descuentosAplicados.append("Pronto Pago el mismo día (15%)\n");
                break;
            case 2:
                precioConDescuento *= 0.85;
                descuentosAplicados.append("Pronto Pago el mismo día (15%)\n");
                break;
        }

        if (descuentoServicio()) {
            precioConDescuento += 50;
            descuentosAplicados.append("Servicio Público ($50)\n");
        } else {
            precioConDescuento += 80;
            JOptionPane.showMessageDialog(null, "No es de servicio publico - $80.");
        }

        if (dias == 2){
            if (descuentoTrasladoCuenta()) {
                JOptionPane.showMessageDialog(null, "Este descuento solo aplica si paga con anticipacion.");
            }
        } else {
            if (descuentoTrasladoCuenta()) {
                precioConDescuento *= 0.90;
                descuentosAplicados.append("Traslado de Cuenta (10%)\n");
            }
        }

        carro.setPrecioDescuento(precioConDescuento);
        return "Precio original: $" + precioOriginal + "\n" +
                "Descuentos aplicados:\n" + descuentosAplicados +
                "Precio con descuentos: $" + precioConDescuento + "\n";
    }
}
