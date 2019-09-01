package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TestVotacion {
    private Persona unPostulante;
    private PartidoPolitico unPartido;
    private Lista unaLista;
    private Eleccion unaEleccion;

    @Before
    public void init(){
    this.unPostulante = new Persona("Jorge",Cargo.DIPUTADO);
    this.unPartido = new PartidoPolitico("FrenteDeDerechaFascista", LocalDate.now(),true);
    this.unaLista = new Lista("ListaUno",50,unPartido);
    this.unaEleccion = new Eleccion();
}

    @Test
    public void VerificarLaVigenciaDelPartidoPolitico(){
        Assert.assertEquals(true,unPartido.getVigencia());
    }

    @Test
    public void VerificarLaFechaDeConformacion(){
        Assert.assertEquals(LocalDate.of(2019,9,01),unPartido.getFechaConformacion());
    }

    //
    //          "Saber la cantidad de postulantes por lista."
    @Test
    public void AgregarPostulanteAUnaListaYaCreada(){
        unaLista.agregarPostulante("Roberto",Cargo.PRESIDENTE,unaLista);
        unaLista.agregarPostulante("CosmeFulanito",Cargo.GOBERNADOR,unaLista);
        Assert.assertEquals(2,unaLista.contarCandidatos());
    }

    @Test
    public void VerificarSiSePuedeAgregarCiudadanoComoPostulante(){
        unaLista.agregarPostulante("Jesus",Cargo.CIUDADANO,unaLista);
        Assert.assertEquals(0,unaLista.contarCandidatos());
    }

    //
    //          "Saber la cantidad de listas que se presentan a una elección."
    @Test
    public void verificarSiLaListaEntraEnLaEleccion(){
        unaLista.agregarPostulante("Jorgue",Cargo.PRESIDENTE,unaLista);
        unaLista.agregarPostulante("Nico del caño fumandose un caño",Cargo.GOBERNADOR,unaLista);
        unaEleccion.agregarLista(unPartido,unaLista);
        Assert.assertEquals(1,unaEleccion.contarListas());
    }


    //
    //          "Impedir que un partido que no está vigente presente una lista para una elección."
    @Test
    public void AgregarVariasListasYVerificarSuVigencia(){

        PartidoPolitico otroPartido = new PartidoPolitico("Kukas", LocalDate.now(),false);
        Lista otraLista = new Lista("ListaUno",50,unPartido);

        unaEleccion.agregarLista(unPartido,unaLista);
        unaEleccion.agregarLista(otroPartido,otraLista);

        Assert.assertEquals(1,unaEleccion.contarListas());
    }

    //
    //          "Permitir que un partido no vigente que recupera su vigencia, puede presentar lista para una elección."
     @Test
    public void CrearPartidoSinVigenciaCambiarlaYSumarloALaEleccion(){

        PartidoPolitico otroPartido = new PartidoPolitico("FrenteDeIzquierdaComunista", LocalDate.now(),false);
        Lista otraLista = new Lista("ListaUno",50,unPartido);

        unaEleccion.agregarLista(otroPartido,otraLista);
        Assert.assertEquals(0,unaEleccion.contarListas());

        otroPartido.serVigente();
        unaEleccion.agregarLista(otroPartido,otraLista);
        Assert.assertEquals(1,unaEleccion.contarListas());
    }
}
