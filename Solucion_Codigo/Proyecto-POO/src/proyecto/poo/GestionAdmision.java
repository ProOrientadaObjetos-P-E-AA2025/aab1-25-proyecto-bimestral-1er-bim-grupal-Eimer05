package proyecto.poo;
import java.util.*;
import java.io.*;

public class GestionAdmision {
    private List<Carrera> carreras;
    private List<Postulante> postulantes;
    private static final String ARCHIVO = "datos_admision.txt";

    public GestionAdmision() {
        carreras = new ArrayList<>();
        postulantes = new ArrayList<>();
        cargarDatosDesdeArchivo();
        if (carreras.isEmpty()) {
            inicializarCarreras();
        }
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    private void inicializarCarreras() {
        carreras.add(new Carrera("Administración de Empresas", 50, 65, false, 0));
        carreras.add(new Carrera("Agropecuaria", 40, 60, false, 0));
        carreras.add(new Carrera("Alimentos", 40, 60, false, 0));
        carreras.add(new Carrera("Artes Escénicas", 30, 50, true, 30));
        carreras.add(new Carrera("Artes Visuales", 30, 50, true, 30));
        carreras.add(new Carrera("Biología", 35, 60, false, 0));
        carreras.add(new Carrera("Contabilidad y Auditoría", 50, 65, false, 0));
        carreras.add(new Carrera("Derecho", 40, 65, false, 0));
        carreras.add(new Carrera("Economía", 40, 65, false, 0));
        carreras.add(new Carrera("Finanzas", 40, 65, false, 0));
        carreras.add(new Carrera("Geología", 35, 60, false, 0));
        carreras.add(new Carrera("Ingeniería Ambiental", 40, 65, false, 0));
        carreras.add(new Carrera("Pedagogía de los Idiomas Nacionales y Extranjeros", 30, 50, true, 30));
        carreras.add(new Carrera("Psicopedagogía", 30, 50, true, 30));
        carreras.add(new Carrera("Telecomunicaciones", 40, 65, false, 0));
        carreras.add(new Carrera("Arquitectura", 30, 65, false, 0));
        carreras.add(new Carrera("Bioquímica y Farmacia", 30, 65, false, 0));
        carreras.add(new Carrera("Computación", 40, 65, false, 0));
        carreras.add(new Carrera("Enfermería", 40, 65, false, 0));
        carreras.add(new Carrera("Fisioterapia", 30, 60, false, 0));
        carreras.add(new Carrera("Gastronomía", 35, 50, true, 30));
        carreras.add(new Carrera("Ingeniería Civil", 40, 65, false, 0));
        carreras.add(new Carrera("Ingeniería Industrial", 40, 65, false, 0));
        carreras.add(new Carrera("Ingeniería Química", 35, 65, false, 0));
        carreras.add(new Carrera("Medicina", 30, 75, false, 0));
        carreras.add(new Carrera("Nutrición y Dietética", 35, 60, false, 0));
        carreras.add(new Carrera("Psicología", 40, 65, false, 0));
        carreras.add(new Carrera("Psicología Clínica", 30, 65, false, 0));
    }

    public void procesarPostulante(Postulante p) {
        postulantes.add(p);
        Carrera carrera = buscarCarreraPorNombre(p.getCarreraElegida());

        if (carrera == null) {
            System.out.println("Carrera no encontrada.");
            return;
        }

        double puntajeFinal = p.calcularPuntajeFinal();

        boolean puedeIngresar = false;

        if (!carrera.isRequiereExamenDiagnostico()) {
            if (puntajeFinal >= carrera.getPuntajeMinimo()) {
                puedeIngresar = true;
            }
        } else {
            if (puntajeFinal >= carrera.getLimiteDiagnostico()) {
                puedeIngresar = true;
            } else {
                System.out.println("Debe aprobar curso de nivelación para ingresar a " + carrera.getNombre());
            }
        }

        if (puedeIngresar) {
            if (carrera.getCuposOcupados() < carrera.getCupos()) {
                carrera.setCuposOcupados(carrera.getCuposOcupados() + 1);
                carrera.setAdmitidos(carrera.getAdmitidos() + 1);
                System.out.println("Admitido en " + carrera.getNombre());
            } else {
                carrera.setRechazados(carrera.getRechazados() + 1);
                System.out.println("No hay cupos disponibles para " + carrera.getNombre() + ". Postulante rechazado.");
            }
        } else {
            carrera.setRechazados(carrera.getRechazados() + 1);
            System.out.println("No alcanza el puntaje mínimo para " + carrera.getNombre());
        }
    }

    private Carrera buscarCarreraPorNombre(String nombre) {
        for (Carrera c : carreras) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public void mostrarEstadisticasFinales() {
        System.out.println("\n--- Estadísticas Finales ---");

        System.out.println("\nCarreras con menos del 50% de cupos ocupados:");
        for (Carrera c : carreras) {
            double porcentaje = (c.getCupos() == 0) ? 0 : ((double) c.getCuposOcupados() / c.getCupos()) * 100;
            if (porcentaje < 50) {
                System.out.printf("  %s - %.2f%% de cupos ocupados%n", c.getNombre(), porcentaje);
            }
        }

        System.out.println("\nCarreras que tuvieron que rechazar postulantes por falta de cupos:");
        for (Carrera c : carreras) {
            if (c.getRechazados() > 0) {
                System.out.printf("  %s - %d postulantes rechazados%n", c.getNombre(), c.getRechazados());
            }
        }
    }
    private void cargarDatosDesdeArchivo() {
    try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos[0].equals("CARRERA")) {
                Carrera c = new Carrera(
                    datos[1], 
                    Integer.parseInt(datos[2]), 
                    Double.parseDouble(datos[3].replace(',', '.')),  
                    Boolean.parseBoolean(datos[4]), 
                    Double.parseDouble(datos[5].replace(',', '.'))   
                );
                c.setAdmitidos(Integer.parseInt(datos[6]));
                c.setRechazados(Integer.parseInt(datos[7]));
                c.setCuposOcupados(Integer.parseInt(datos[8]));
                carreras.add(c);
            } else if (datos[0].equals("POSTULANTE")) {
                Postulante p = new Postulante(
                    datos[1], 
                    Double.parseDouble(datos[2].replace(',', '.')),  
                    Boolean.parseBoolean(datos[3]), 
                    Boolean.parseBoolean(datos[4]), 
                    Double.parseDouble(datos[5].replace(',', '.')),  
                    datos[6]
                );
                postulantes.add(p);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("No se encontró archivo de datos. Se iniciará nuevo registro.");
    } catch (IOException e) {
        System.out.println("Error al leer archivo: " + e.getMessage());
    }
}

    public void guardarDatosEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Carrera c : carreras) {
                pw.println(String.format("CARRERA;%s;%d;%.2f;%b;%.2f;%d;%d;%d",
                    c.getNombre(), c.getCupos(), c.getPuntajeMinimo(),
                    c.isRequiereExamenDiagnostico(), c.getLimiteDiagnostico(),
                    c.getAdmitidos(), c.getRechazados(), c.getCuposOcupados()));
            }
            
            for (Postulante p : postulantes) {
                pw.println(String.format("POSTULANTE;%s;%.2f;%b;%b;%.2f;%s",
                    p.getNombre(), p.getPuntajeExamen(), p.isAbanderado(),
                    p.isBachilleratoAfin(), p.getPorcentajeDiscapacidad(),
                    p.getCarreraElegida()));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }
}