package domain;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    private String nombre;
    private int numero;
    private PartidoPolitico unPartido;
    private List<Persona> candidatos;

    public Lista(String nombre, int numero, PartidoPolitico unPartido) {
        this.nombre = nombre;
        this.numero = numero;
        this.unPartido = unPartido;
        this.candidatos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public PartidoPolitico getUnPartido() {
        return unPartido;
    }

    public void setUnPartido(PartidoPolitico unPartido) {
        this.unPartido = unPartido;
    }

    public void agregarPostulante(String nombre, Cargo unCargo, Lista unaLista){
        if(unCargo != Cargo.CIUDADANO){
            unaLista.candidatos.add(new Persona(nombre,unCargo));
        }
    }

    public int contarCandidatos(){
        return this.candidatos.size();
    }
}
