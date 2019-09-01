package domain;

public class Persona implements Votable{
    private String nombre;
    private Cargo cargo;
    private int edad;
    private boolean yaVoto;
    private long cantidadVotosCandidato;

    public Persona(String nombre, Cargo cargo,int edad) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.edad = edad;
        this.yaVoto = false;
        this.cantidadVotosCandidato = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean yaVoto() {
        return yaVoto;
    }

    public void setYaVoto(boolean yaVoto) {
        this.yaVoto = yaVoto;
    }

    public long getCantidadVotosCandidato() {
        return this.cantidadVotosCandidato;
    }

    public void setCantidadVotosCandidato(long cantidadVotosCandidato) {
        this.cantidadVotosCandidato = cantidadVotosCandidato;
    }

    public boolean verificarEdadYSiVoto(Persona unaPersona){
        boolean puedeVotar = false;
        if(unaPersona.getEdad() >= 16 && !unaPersona.yaVoto()){
            puedeVotar = true;
        }
        return puedeVotar;
    }

    public void votarCandidato(){
        this.cantidadVotosCandidato++;
    }

    public void votarA(Lista unaLista,Eleccion unaEleccion){
        if(verificarEdadYSiVoto(this)){
            unaLista.serVotadaPor(this);
            this.setYaVoto(true);
            unaEleccion.setCantVotos(unaEleccion.getCantVotos()+1);
        }

    }

    public void votarA(Lista unaLista, Cargo unCargo,Eleccion unaEleccion){
        if(verificarEdadYSiVoto(this)){
            unaLista.serVotadoPor(this,unCargo);
            this.setYaVoto(true);
            unaEleccion.setCantVotos(unaEleccion.getCantVotos()+1);
        }
    }




}
