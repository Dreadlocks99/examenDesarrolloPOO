package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TestVotacion {
    private Persona unaPersona;
    private PartidoPolitico unPartido;
    private Lista unaLista;
    private Eleccion unaEleccion;

    @Before
    public void init(){
    this.unaPersona = new Persona("Jorge",Cargo.DIPUTADO,50);
    this.unPartido = new PartidoPolitico("FrenteDeDerechaFascista", LocalDate.now(),true,true);
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
        unaLista.agregarPostulante("Roberto",Cargo.PRESIDENTE,unaLista,50);
        unaLista.agregarPostulante("CosmeFulanito",Cargo.GOBERNADOR,unaLista,50);
        Assert.assertEquals(2,unaLista.contarCandidatos());
    }

    @Test
    public void VerificarSiSePuedeAgregarCiudadanoComoPostulante(){
        unaLista.agregarPostulante("Jesus",Cargo.CIUDADANO,unaLista,50);
        Assert.assertEquals(0,unaLista.contarCandidatos());
    }

    //
    //          "Saber la cantidad de listas que se presentan a una elección."
    @Test
    public void VerificarSiLaListaEntraEnLaEleccion(){
        unaLista.agregarPostulante("Jorgue",Cargo.PRESIDENTE,unaLista,50);
        unaLista.agregarPostulante("Nico del caño fumandose un caño",Cargo.GOBERNADOR,unaLista,50);
        unaEleccion.agregarLista(unPartido,unaLista);
        Assert.assertEquals(1,unaEleccion.contarListas());
    }


    //
    //          "Impedir que un partido que no está vigente presente una lista para una elección."
    @Test
    public void AgregarVariasListasYVerificarSuVigencia(){

        PartidoPolitico otroPartido = new PartidoPolitico("Kukas", LocalDate.now(),false,true);
        Lista otraLista = new Lista("ListaUno",50,unPartido);

        unaEleccion.agregarLista(unPartido,unaLista);
        unaEleccion.agregarLista(otroPartido,otraLista);

        Assert.assertEquals(1,unaEleccion.contarListas());
    }

    //
    //          "Permitir que un partido no vigente que recupera su vigencia, puede presentar lista para una elección."
     @Test
    public void CrearPartidoSinVigenciaCambiarlaYSumarloALaEleccion(){

        PartidoPolitico otroPartido = new PartidoPolitico("FrenteDeIzquierdaComunista", LocalDate.now(),false,true);
        Lista otraLista = new Lista("ListaUno",50,unPartido);

        unaEleccion.agregarLista(otroPartido,otraLista);
        Assert.assertEquals(0,unaEleccion.contarListas());

        otroPartido.serVigente();
        unaEleccion.agregarLista(otroPartido,otraLista);
        Assert.assertEquals(1,unaEleccion.contarListas());
    }

    //------------------------------------------------------------------------------------------------------------------
    //SEGUNDA ITERACION:

    //          Se puede preguntar a una persona si ya votó.
    @Test
    public void PreguntarSiYaVoto(){

        Assert.assertFalse(unaPersona.yaVoto());

        unaPersona.votarA(unaLista,unaEleccion);

        Assert.assertTrue(unaPersona.yaVoto());
    }

    //          Su solución impide que una persona que ya votó vuelva a hacerlo.
    @Test
    public void AgregarVotoAListaEntera(){
        unaPersona.votarA(unaLista,unaEleccion);
        unaPersona.votarA(unaLista,unaEleccion);
        Assert.assertEquals(1,unaLista.getCantidadDeVotos());
    }

    //          Se puede saber la cantidad de votos que tuvo una lista para un determinado cargo.

    @Test
    public void AgregarVotoACargoEspecifico(){
        unaLista.agregarPostulante("Juan",Cargo.PRESIDENTE,unaLista,50);
        unaPersona.votarA(unaLista,Cargo.PRESIDENTE,unaEleccion);

        Assert.assertEquals(1,unaLista.cantidadVotosDe(Cargo.PRESIDENTE));

        Persona otraPersona = new Persona("Roberto",Cargo.CIUDADANO,20);
        otraPersona.votarA(unaLista,Cargo.PRESIDENTE,unaEleccion);

        Persona unaUltimaPersona = new Persona("Carlos",Cargo.CIUDADANO,20);
        unaUltimaPersona.votarA(unaLista,Cargo.GOBERNADOR,unaEleccion);

        Assert.assertEquals(2,unaLista.cantidadVotosDe(Cargo.PRESIDENTE));
    }

    //          Se puede saber la cantidad de votos totales emitidos en una elección.
    @Test
    public void SaberLaCantidadDeVotosEnUnaEleccion(){
        unaLista.agregarPostulante("Juan",Cargo.PRESIDENTE,unaLista,50);
        unaPersona.votarA(unaLista,Cargo.PRESIDENTE,unaEleccion);

        Persona otraPersona = new Persona("Roberto",Cargo.CIUDADANO,20);
        otraPersona.votarA(unaLista,Cargo.PRESIDENTE,unaEleccion);

        Assert.assertEquals(2,unaEleccion.getCantVotos());

        Persona unaUltimaPersona = new Persona("Carlos",Cargo.CIUDADANO,20);
        unaUltimaPersona.votarA(unaLista,Cargo.GOBERNADOR,unaEleccion);

        Assert.assertEquals(3,unaEleccion.getCantVotos());
    }

    @Test
    public void VotarUnaListaYVerificarSiVotaTodosLosCandidatos(){
        unaLista.agregarPostulante("Juan",Cargo.PRESIDENTE,unaLista,50);
        unaLista.agregarPostulante("Julian",Cargo.GOBERNADOR,unaLista,99);
        unaLista.agregarPostulante("Jesus",Cargo.VICEPRESIDENTE,unaLista,30);
        unaLista.agregarPostulante("Pablo",Cargo.SENADOR,unaLista,40);
        unaPersona.votarA(unaLista,unaEleccion);

        Persona otraPersona = new Persona("Roberto",Cargo.CIUDADANO,20);
        otraPersona.votarA(unaLista,unaEleccion);

        Persona unaUltimaPersona = new Persona("Carlos",Cargo.CIUDADANO,20);
        unaUltimaPersona.votarA(unaLista,unaEleccion);

        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.PRESIDENTE));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.GOBERNADOR));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.VICEPRESIDENTE));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.SENADOR));

        Persona votanteQueCortaBoleta = new Persona("Gaston",Cargo.CIUDADANO,20);
        votanteQueCortaBoleta.votarA(unaLista,Cargo.PRESIDENTE,unaEleccion);

        Assert.assertEquals(4,unaLista.cantidadVotosDe(Cargo.PRESIDENTE));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.GOBERNADOR));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.VICEPRESIDENTE));
        Assert.assertEquals(3,unaLista.cantidadVotosDe(Cargo.SENADOR));
    }

    @Test
    public void VerificarQueUnMenorDeEdadNoPuedeVotar(){
        Persona otraPersona = new Persona("Roberto",Cargo.CIUDADANO,15);
        otraPersona.votarA(unaLista,unaEleccion);

        Assert.assertEquals(0,unaLista.getCantidadDeVotos());
        Assert.assertEquals(0,unaEleccion.getCantVotos());
    }

    //------------------------------------------------------------------------------------------------------------------
    //TERCERA ITERACION:

    //No llegue U.U

}
