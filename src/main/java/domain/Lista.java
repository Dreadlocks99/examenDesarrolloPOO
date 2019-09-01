package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lista {
    private String nombre;
    private int numero;
    private PartidoPolitico unPartido;
    private List<Persona> candidatos;

    //SEGUNDA ITERACION
    private long cantidadDeVotos;

    public Lista(String nombre, int numero, PartidoPolitico unPartido) {
        this.nombre = nombre;
        this.numero = numero;
        this.unPartido = unPartido;
        this.candidatos = new ArrayList<>();
        this.cantidadDeVotos = 0;
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

    public List<Persona> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Persona> candidatos) {
        this.candidatos = candidatos;
    }

    public long getCantidadDeVotos() {
        return cantidadDeVotos;
    }

    public void setCantidadDeVotos(long cantidadDeVotos) {
        this.cantidadDeVotos = cantidadDeVotos;
    }

    public void agregarPostulante(String nombre, Cargo unCargo, Lista unaLista,int unaEdad){
        if(unCargo != Cargo.CIUDADANO){
            unaLista.candidatos.add(new Persona(nombre,unCargo,unaEdad));
        }
    }

    public int contarCandidatos(){
        return this.candidatos.size();
    }

    public void serVotadaPor(Persona unaPersona){
            this.cantidadDeVotos += 1;
            candidatos
                    .stream()
                    .forEach(candidatos -> candidatos.votarCandidato());

    }

    public void serVotadoPor(Persona unaPersona, Cargo unCargo){
            candidatos
            .stream()
            .filter(candidato -> candidato.getCargo() == unCargo)
            .forEach(candidato -> candidato.votarCandidato());
    }

    public long cantidadVotosDe(Cargo unCargo){
        List<Persona> votosCandidato = this.candidatos
                .stream()
                .filter(candidato -> candidato.getCargo() == unCargo)
                .collect(Collectors.toList());
        return votosCandidato
                .stream()
                .mapToLong(candidato -> candidato.getCantidadVotosCandidato())
                .sum();
    }
}
