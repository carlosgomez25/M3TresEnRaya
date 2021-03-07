package sample.Jugador;


public class Jugador {
    private String nombre;
    private int[] resultados = {0,0,0};

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void añadirResultado(int posicion) {
        resultados[posicion] += 1;
    }

    public int getResultados(int pos) {
        return resultados[pos];
    }
}

