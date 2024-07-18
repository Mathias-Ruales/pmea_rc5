import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Carro> carros = new ArrayList<>();
        String nombreDueno, placa;
        double precio=0;
        while (true) {
            String[] opciones = {"Añadir carro", "Calcular descuentos e impuestos de un carro", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (eleccion) {
                case 0:
                    nombreDueno = JOptionPane.showInputDialog("Ingrese el nombre del dueño:");
                    placa = JOptionPane.showInputDialog("Ingrese la placa del carro:");
                    boolean ingresoValido = false;
                    do {
                        try {
                            precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del carro:"));
                            if (precio <= 0) {
                                throw new NumberFormatException("Ingrese un número positivo");
                            }
                            ingresoValido = true;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
                        }
                    } while (!ingresoValido);
                    carros.add(new Carro(nombreDueno, placa, precio));
                    break;

                case 1:
                    if (carros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay carros añadidos.");
                        break;
                    }
                    String[] listaCarros = new String[carros.size()];
                    for (int i = 0; i < listaCarros.length; i++) {
                        listaCarros[i] = carros.get(i).getNombre_dueno() + " - " + carros.get(i).getPlaca();
                    }
                    int eleccionCarro = JOptionPane.showOptionDialog(null, "Seleccione un carro", "Lista de Carros",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, listaCarros, listaCarros[0]);
                    Carro carroSeleccionado = carros.get(eleccionCarro);
                    JOptionPane.showMessageDialog(null, carroSeleccionado.calcularDescuento(carroSeleccionado));
                    break;

                case 2:
                    System.exit(0);
                    break;

            }
        }
    }
}