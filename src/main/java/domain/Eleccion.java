package domain;

import java.util.ArrayList;
import java.util.List;

public class Eleccion {

    private List<Lista> listas;
    private long cantVotos;

    public Eleccion() {
        this.listas = new ArrayList<>();
        this.cantVotos = 0;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    public long getCantVotos() {
        return cantVotos;
    }

    public void setCantVotos(long cantVotos) {
        this.cantVotos = cantVotos;
    }

    public void agregarLista(PartidoPolitico unPartido, Lista unaLista){
        if(unPartido.getVigencia()){
            listas.add(unaLista);
        }
    }

    public int contarListas(){
        return listas.size();
    }

}
