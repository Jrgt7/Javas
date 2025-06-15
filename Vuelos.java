import java.beans.DefaultPersistenceDelegate;
import java.util.*;

public class Vuelos {

    static final int MAX_ASIENTOS = 102;
    static int asientosDisponibles = MAX_ASIENTOS;

    static Scanner sc = new Scanner(System.in);

    static class Reserva {
        String nombre;
        String id;
        String tipoBoleto;
        String ruta;
        int horasRestantes;

        Reserva(String nombre, String id, String tipoBoleto, String ruta, int horasRestantes) {
            this.nombre = nombre;
            this.id = id;
            this.tipoBoleto = tipoBoleto;
            this.ruta = ruta;
            this.horasRestantes = horasRestantes;
        }
    }

    static List<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        String opcion;
        do {
            mostrarMenu();
            opcion = sc.nextLine().toUpperCase(); // Leer como String y convertir a mayúscula

            switch (opcion) {
                case "A":
                    reservarVuelo();
                    break;
                case "B":
                    cancelarReserva();
                    break;
                case "C":
                    modificarReserva();
                    break;
                case "D":
                    System.out.println("Gracias por usar el sistema de reservas.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (!opcion.equals("D"));
    }

    static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("A. Reservar vuelo");
        System.out.println("B. Cancelar reserva");
        System.out.println("C. Modificar reserva");
        System.out.println("D. Salir");
        System.out.print("Seleccione una opción: ");
    }

    static void reservarVuelo() {
        if (asientosDisponibles <= 0) {
            System.out.println("No hay asientos disponibles.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Tipo de boleto (Normal, Primera clase, V.I.P.): ");
        String tipoBoleto = sc.nextLine();

        String tipoBoletoLower= tipoBoleto.toLowerCase() ;
        String ruta = "";

        if (tipoBoletoLower.equals("normal")) {
            ruta = "Nacional";
        } else if (tipoBoletoLower.equals("primera clase") || tipoBoletoLower.equals("v.i.p.")) {
            System.out.print("Ruta (Nacional/Internacional): ");
            ruta = sc.nextLine();
        } else {
            System.out.println("Tipo de boleto inválido.");
            return;
        }

        System.out.print("¿Cuántas horas faltan para el vuelo? ");
        int horasRestantes = Integer.parseInt(sc.nextLine());

        if (horasRestantes < 48) {
            System.out.println("La reserva debe hacerse al menos 48 horas antes del vuelo.");
            return;
        }

        System.out.print("Cantidad de maletas: ");
        int maletas = Integer.parseInt(sc.nextLine());
        System.out.print("Peso total del equipaje (kg): ");
        double peso = Double.parseDouble(sc.nextLine());

        if (maletas > 2 || peso > 20) {
            System.out.println("Equipaje excede el límite (máx 2 maletas y 20kg).");
            return;
        }

        reservas.add(new Reserva(nombre, id, tipoBoleto, ruta, horasRestantes));
        asientosDisponibles--;

        System.out.println("Trámite realizado con éxito.");
    }

    static void cancelarReserva() {
        System.out.print("Ingrese el ID de la reserva: ");
        String id = sc.nextLine();
        Reserva reserva = buscarReserva(id);

        if (reserva == null) {
            System.out.println("Reserva no encontrada.");
            return;
        }

        if (reserva.horasRestantes < 48) {
            System.out.println("Cancelación fuera de plazo. Multa del 20%.");
        } else {
            System.out.println("Reserva cancelada.");
        }

        reservas.remove(reserva);
        asientosDisponibles++;
    }

    static void modificarReserva() {
        System.out.print("Ingrese el ID de la reserva: ");
        String id = sc.nextLine();
        Reserva reserva = buscarReserva(id);

        if (reserva == null) {
            System.out.println("Reserva no encontrada.");
            return;
        }

        if (reserva.horasRestantes < 48) {
            System.out.println("No se permiten modificaciones con menos de 48 horas de anticipación.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        reserva.nombre = sc.nextLine();
        System.out.print("Nueva ruta: ");
        reserva.ruta = sc.nextLine();
        System.out.println("Reserva modificada.");
    }

    static Reserva buscarReserva(String id) {
        for (Reserva r : reservas) {
            if (r.id.equals(id)) {
                return r;
            }
        }
        return null;
    }
}
