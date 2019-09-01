package domain;

import java.util.ArrayList;
import java.util.List;

public class Eleccion {

    private List<Lista> listas;

    public Eleccion() {
        this.listas = new ArrayList<>();
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
