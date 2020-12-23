package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.Nivel;

@Local
public interface GradoFacadeLocal {

    void create(Grado grado);

    void edit(Grado grado);

    void remove(Grado grado);

    Grado find(Object id);

    List<Grado> findAll();

    List<Grado> findRange(int[] range);
    
    List<Grado> findAllActive();

    int count();
    
    public List<Grado> obtenerGradosPorAmieInstitucionEstado(String amie, List<Character> estadosGrados, Short codigoRegimenAnioLectivo, int mostrarGrado);
    
    public List<Grado> obtenerGradosPorAmieInstitucionEstadoConMallaCargada(String amie, List<Character> estadosGrados, Short codigoRegimenAnioLectivo, int mostrarGrado);
    
    public Set<Grado> obtenerTodosGradosParalelosInstitucionPorRegimenAnioLectivo(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec);
    
    public List<Grado> obtenerTodosGradosParalelosInstitucionPorRegimenAnioLectivoYNiveles(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec, List<Nivel> niveles, Short codigoJornada, Short codigoModalidad, Short codigoTipoEducacion, Short codigoEspecialidad);
    
    public Set<Grado> obtenerGradosCursosParalelosPorEstadoRegimenAnioLectivoEstado(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec, List<Short> codigosNivelesTutor, int mostrarGrado);
    
    public List<Grado> obtenerGradosPorNemonicoMateriaEstadoRegimenAnioLectivoInstitucion(String nemonicoMateria, Integer codigoInstitucion, Short codigoRegAniLec, List<Character> estadosGrados, int mostrarGrado);
    
    public List<Grado> obtenerGradoEbja();
    
    public Grado obtenerGrado(String nemonico);
}
