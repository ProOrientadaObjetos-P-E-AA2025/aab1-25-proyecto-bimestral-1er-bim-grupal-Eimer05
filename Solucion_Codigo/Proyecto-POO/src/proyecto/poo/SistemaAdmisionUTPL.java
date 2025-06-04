package proyecto.poo;
import java.util.*;
import java.text.*;

public class SistemaAdmisionUTPL {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        
        GestionAdmision gestion = new GestionAdmision();
        Scanner tcl = new Scanner(System.in);
        String continuar;

        System.out.println("Bienvenido al Sistema de Admisión UTPL");
        System.out.print("Ingrese la fecha actual (formato dd-MM-yyyy, entre 23-05-2025 y 23-06-2025): ");
        String fechaStr = tcl.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        formato.setLenient(false);
        Date fechaIngreso;
        Date fechaInicio, fechaFin;

        try {
            fechaIngreso = formato.parse(fechaStr);
            fechaInicio = formato.parse("23-05-2025");
            fechaFin = formato.parse("23-06-2025");

            if (fechaIngreso.before(fechaInicio) || fechaIngreso.after(fechaFin)) {
                System.out.println("No es posible ingresar postulantes fuera del periodo de admisión (23-05-2025 a 23-06-2025).");
                tcl.close();
                return;
            }
        } catch (ParseException e) {
            System.out.println("Fecha inválida. Debe ingresar en formato dd-MM-yyyy.");
            tcl.close();
            return;
        }

        List<String> listaCarreras = new ArrayList<>();
        for (Carrera c : gestion.getCarreras()) {
            listaCarreras.add(c.getNombre());
        }

        do {
            System.out.println("\nIngrese los datos del postulante:");

            String nombre = "";
            while (nombre.trim().isEmpty()) {
                System.out.print("Nombre: ");
                nombre = tcl.nextLine();
                if (nombre.trim().isEmpty()) {
                    System.out.println("El nombre no puede estar vacío.");
                }
            }

            double puntaje = -1;
            do {
                System.out.print("Puntaje obtenido (sobre 100): ");
                if (tcl.hasNextDouble()) {
                    puntaje = tcl.nextDouble();
                    tcl.nextLine();
                    if (puntaje < 0 || puntaje > 100) {
                        System.out.println("El puntaje debe estar entre 0 y 100.");
                        puntaje = -1;
                    }
                } else {
                    System.out.println("Por favor ingrese un número válido.");
                    tcl.next();
                }
            } while (puntaje == -1);

            boolean abanderado = false;
            do {
                System.out.print("¿Fue abanderado? (si/no): ");
                String resp = tcl.nextLine().trim().toLowerCase();
                if (resp.equals("si")) {
                    abanderado = true;
                    break;
                } else if (resp.equals("no")) {
                    abanderado = false;
                    break;
                } else {
                    System.out.println("Respuesta inválida. Por favor ingrese 'si' o 'no'.");
                }
            } while (true);

            boolean bachilleratoAfin = false;
            do {
                System.out.print("¿Su bachillerato es afín a la carrera? (si/no): ");
                String resp = tcl.nextLine().trim().toLowerCase();
                if (resp.equals("si")) {
                    bachilleratoAfin = true;
                    break;
                } else if (resp.equals("no")) {
                    bachilleratoAfin = false;
                    break;
                } else {
                    System.out.println("Respuesta inválida. Por favor ingrese 'si' o 'no'.");
                }
            } while (true);

            double discapacidad = -1;
            do {
                System.out.print("Porcentaje de discapacidad (0 si no aplica): ");
                if (tcl.hasNextDouble()) {
                    discapacidad = tcl.nextDouble();
                    tcl.nextLine();
                    if (discapacidad < 0 || discapacidad > 100) {
                        System.out.println("El porcentaje debe estar entre 0 y 100.");
                        discapacidad = -1;
                    }
                } else {
                    System.out.println("Por favor ingrese un número válido.");
                    tcl.next();
                }
            } while (discapacidad == -1);

            System.out.println("\nSeleccione una carrera por su número:");
            for (int i = 0; i < listaCarreras.size(); i++) {
                System.out.println((i + 1) + ". " + listaCarreras.get(i));
            }

            int opcionCarrera;
            do {
                System.out.print("Opción (1 - " + listaCarreras.size() + "): ");
                if (tcl.hasNextInt()) {
                    opcionCarrera = tcl.nextInt();
                    tcl.nextLine();
                    if (opcionCarrera < 1 || opcionCarrera > listaCarreras.size()) {
                        System.out.println("Número inválido, intente de nuevo.");
                        opcionCarrera = -1;
                    }
                } else {
                    System.out.println("Ingrese un número válido.");
                    tcl.next();
                    opcionCarrera = -1;
                }
            } while (opcionCarrera == -1);

            String carreraSeleccionada = listaCarreras.get(opcionCarrera - 1);

            Postulante postulante = new Postulante(nombre, puntaje, abanderado, bachilleratoAfin, discapacidad, carreraSeleccionada);

            System.out.println("\nCalculando puntaje final...");
            double puntajeFinal = postulante.calcularPuntajeFinal();
            System.out.println("Puntaje final del postulante: " + puntajeFinal);

            gestion.procesarPostulante(postulante);

            System.out.print("\n¿Desea ingresar otro postulante? (s/n): ");
            continuar = tcl.nextLine();

        } while (continuar.equalsIgnoreCase("s"));

        gestion.mostrarEstadisticasFinales();
        
        try {
            gestion.guardarDatosEnArchivo();
        } catch (Exception e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
        
        tcl.close();
    }
}