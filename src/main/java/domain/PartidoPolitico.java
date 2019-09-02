package domain;

import java.time.LocalDate;

public class PartidoPolitico extends AgrupacionPolitica{
    private String nombre;
    private LocalDate fechaConformacion;
    private boolean vigencia;

    //TERCERA ITERACION:
    private boolean presentoLista;
    //----------------------------------------------------------------------------------------------------------------

    public PartidoPolitico(String nombre, LocalDate fechaConformacion, boolean vigencia,boolean presentoLista) {
        this.nombre = nombre;
        this.fechaConformacion = fechaConformacion;
        this.vigencia = vigencia;
        this.presentoLista = presentoLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaConformacion() {
        return fechaConformacion;
    }

    public void setFechaConformacion(LocalDate fechaConformacion) {
        this.fechaConformacion = fechaConformacion;
    }

    public boolean getVigencia() {
        return vigencia;
    }

    public void serVigente(){
        this.vigencia = true;
    }

    public void noSerVigente(){
        this.vigencia = false;
    }


}
