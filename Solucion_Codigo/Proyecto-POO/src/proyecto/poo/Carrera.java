package proyecto.poo;
public class Carrera {
    private String nombre;
    private int cupos;
    private double puntajeMinimo;
    private boolean requiereExamenDiagnostico;
    private double limiteDiagnostico;
    private int admitidos;
    private int rechazados;
    private int cuposOcupados;

    public Carrera(String nombre, int cupos, double puntajeMinimo, boolean requiereExamenDiagnostico, double limiteDiagnostico) {
        this.nombre = nombre;
        this.cupos = cupos;
        this.puntajeMinimo = puntajeMinimo;
        this.requiereExamenDiagnostico = requiereExamenDiagnostico;
        this.limiteDiagnostico = limiteDiagnostico;
        this.admitidos = 0;
        this.rechazados = 0;
        this.cuposOcupados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public double getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(double puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    public boolean isRequiereExamenDiagnostico() {
        return requiereExamenDiagnostico;
    }

    public void setRequiereExamenDiagnostico(boolean requiereExamenDiagnostico) {
        this.requiereExamenDiagnostico = requiereExamenDiagnostico;
    }

    public double getLimiteDiagnostico() {
        return limiteDiagnostico;
    }

    public void setLimiteDiagnostico(double limiteDiagnostico) {
        this.limiteDiagnostico = limiteDiagnostico;
    }

    public int getAdmitidos() {
        return admitidos;
    }

    public void setAdmitidos(int admitidos) {
        this.admitidos = admitidos;
    }

    public int getRechazados() {
        return rechazados;
    }

    public void setRechazados(int rechazados) {
        this.rechazados = rechazados;
    }

    public int getCuposOcupados() {
        return cuposOcupados;
    }

    public void setCuposOcupados(int cuposOcupados) {
        this.cuposOcupados = cuposOcupados;
    }
}