package proyecto.poo;
public class Postulante {
    private String nombre;
    private double puntajeExamen;
    private boolean abanderado;
    private boolean bachilleratoAfin;
    private double porcentajeDiscapacidad;
    private String carreraElegida;

    public Postulante(String nombre, double puntajeExamen, boolean abanderado, boolean bachilleratoAfin, double porcentajeDiscapacidad, String carreraElegida) {
        this.nombre = nombre;
        this.puntajeExamen = puntajeExamen;
        this.abanderado = abanderado;
        this.bachilleratoAfin = bachilleratoAfin;
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
        this.carreraElegida = carreraElegida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPuntajeExamen() {
        return puntajeExamen;
    }

    public void setPuntajeExamen(double puntajeExamen) {
        this.puntajeExamen = puntajeExamen;
    }

    public boolean isAbanderado() {
        return abanderado;
    }

    public void setAbanderado(boolean abanderado) {
        this.abanderado = abanderado;
    }

    public boolean isBachilleratoAfin() {
        return bachilleratoAfin;
    }

    public void setBachilleratoAfin(boolean bachilleratoAfin) {
        this.bachilleratoAfin = bachilleratoAfin;
    }

    public double getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(double porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

    public String getCarreraElegida() {
        return carreraElegida;
    }

    public void setCarreraElegida(String carreraElegida) {
        this.carreraElegida = carreraElegida;
    }

    public double calcularPuntajeFinal() {
        double puntajeFinal = puntajeExamen;

        if (abanderado) {
            puntajeFinal += 5;
        }
        if (bachilleratoAfin) {
            puntajeFinal += 2;
        }
        if (porcentajeDiscapacidad > 35) {
            puntajeFinal += 3;
        }

        return puntajeFinal > 100 ? 100 : puntajeFinal;
    }
}