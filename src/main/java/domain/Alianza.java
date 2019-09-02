package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Alianza extends AgrupacionPolitica{
    private List<Alianza> cantidadAlianzas;
    private List<PartidoPolitico> cantidadPartidos;
    private boolean presentoLista;
    private String nombre;

    public Alianza(String nombre) {
        this.presentoLista = false;
        this.nombre = nombre;
        this.cantidadAlianzas = new ArrayList<>();
        this.cantidadPartidos = new ArrayList<>();
    }

    public void crearUnaAlianza(String nombreAlianzaNueva){
        this.cantidadAlianzas.add(new Alianza(nombreAlianzaNueva));
    }

    public void crearUnPartido(String nombrePartidoNuevo,L){
        this.cantidadPartidos.add(new PartidoPolitico(nombrePartidoNuevo, LocalDate.now(),));
    }

    public boolean PresentoLista() {
        return presentoLista;
    }
    public void setPresentoLista(boolean presentoLista) {
        this.presentoLista = presentoLista;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
