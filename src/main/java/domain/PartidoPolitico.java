package domain;

import java.time.LocalDate;

public class PartidoPolitico {
    private String nombre;
    private LocalDate fechaConformacion;
    private boolean vigencia;

    public PartidoPolitico(String nombre, LocalDate fechaConformacion, boolean vigencia) {
        this.nombre = nombre;
        this.fechaConformacion = fechaConformacion;
        this.vigencia = vigencia;
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
