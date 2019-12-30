package com.moimah.hibernate;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import com.moimah.entities.Estancias;
import com.moimah.entities.Estudiantes;
import com.moimah.entities.Residencias;
import com.moimah.entities.Residenciasobservaciones;
import com.moimah.entities.Universidades;

public class HibernateController {

	private StandardServiceRegistry sr;
	private SessionFactory sf;
	private Session session;

	public void start() {

		//Inicialización del SessionFactory
		sr = new StandardServiceRegistryBuilder().configure().build();		
				
		sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();				
						
	    //Apertura de una sesión (e inicio de una transacción)		
	    session = sf.openSession();
		

	}

	/**
	 * Agrega una residencia mediante una transaccion 
	 * @param nuevaResidencia
	 */

	public void insertResidencia(Residencias nuevaResidencia) {

		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.save(nuevaResidencia);

		// Commit de la transacción
		session.getTransaction().commit();

	}

	/**
	 * Agrega una residencia + observacion mediante una transaccion 
	 * @param nuevaResidencia
	 * @param observacion
	 */

	public void insertResidenciaObservacion(Residencias nuevaResidencia, Residenciasobservaciones observacion) {
		
		
		// Comienzo de transacción
		session.beginTransaction();

		// Vincular observación a la residencia
		nuevaResidencia.setResidenciasobservaciones(observacion);
		observacion.setResidencias(nuevaResidencia);

		// Almacenamos objetos
		session.save(nuevaResidencia);
		session.save(observacion);

		// Commit de la transacción
		session.getTransaction().commit();

	}

	
	/**
	 * Agregar una nueva universidad
	 * @param nuevaUniversidad
	 */

	public void insertUniversidad(Universidades universidad) {

		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.save(universidad);

		// Commit de la transacción
		session.getTransaction().commit();

	}
	

	/**
	 * Agrega o edita una estancia mediante una transaccion 
	 * @param estancia
	 */
	public void insertEstancia(Estancias estancia) {
		
		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.save(estancia);

		// Commit de la transacción
		session.getTransaction().commit();

	}
	
		
	/**
	 * Agrega o edita una estancia mediante una transaccion 
	 * @param nuevoEstudiante
	 */
	public void insertEstudiante(Estudiantes nuevoEstudiante) {

		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.saveOrUpdate(nuevoEstudiante);

		// Commit de la transacción
		session.getTransaction().commit();

	}
	
	
	/**
	 * Elimina una universidad mediante una transaccion
	 * @param universidad
	 */
	public void deleteUniversidad(Universidades universidad) {
		
		session.clear();
		
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.delete(universidad);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	
	/**
	 * Elimina un estudiante mediante una transacción
	 * @param estudiante
	 */
	public void deleteEstudiante(Estudiantes estudiante) {
		
		session.clear();
		
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.delete(estudiante);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	
	/**
	 * Elimina una residencia mediante una transacción
	 * @param residencia
	 */	
	public void deleteResidencia(Residencias residencia) {
		
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.delete(residencia);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	/**
	 * Borra una residencia usando HQL 
	 * @param codResidencia
	 */
	
	public void deleteResidencia(int codResidencia) {
		
		// Comienzo de transacción
		session.beginTransaction();
		
		Query query = session.createQuery("DELETE FROM Residencias r where r.codResidencia = :codResidencia ");
		query.setParameter("codResidencia", codResidencia);
		
		int result = query.executeUpdate();
		
		//System.out.println("Se ha eliminado " + result);
		
		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	/**
	 * Borra una estancia recibiendo un objeto estancia
	 * @param estancia
	 */

	public void deleteEstancia(Estancias estancia) {
		//Borrar la cache
		session.clear();
		
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.delete(estancia);

		// Commit de la transacción
		session.getTransaction().commit();

	}
	
	
	/**
	 * Borra una estancia con HQL
	 * @param codEstancia
	 */
	
	public void deleteEstancia(int codEstancia) {

		// Comienzo de transacción
		session.beginTransaction();

		Query query = session.createQuery("DELETE FROM Estancias e where e.codEstancia = :codEstancia ");
		query.setParameter("codResidencia", codEstancia);

		int result = query.executeUpdate();

		// System.out.println("Se ha eliminado " + result);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	
	
	/**
	 * Actualiza un estudiante mediante una transaccion
	 * @param estancia
	 */		
	public void updateEstudiante(Estudiantes estudiante) {
	
		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.update(estudiante);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	
	
	/**
	 * Actualiza una universidad mediante una transaccion
	 * @param estancia
	 */		
	public void updateUniversidad(Universidades universidad) {
	
		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.update(universidad);

		// Commit de la transacción
		session.getTransaction().commit();
		
	}
	

	/**
	 * Actualiza una estancia mediante una transaccion
	 * @param estancia
	 */		
	public void updateEstancia(Estancias estancia) {
		
		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.update(estancia);

		// Commit de la transacción
		session.getTransaction().commit();
	}
	
	
	
	/**
	 * Actualiza una residencia mediante una transacción
	 * @param residencia
	 */	
	public void updateResidencia(Residencias residencia) {	
		
		session.clear();
		// Comienzo de transacción
		session.beginTransaction();

		// Almacenamos objeto
		session.update(residencia);
		// Commit de la transacción
		session.getTransaction().commit();

	}
	
	/**
	 * Actualiza residencia y observacion recibiendo objetos de este tipo
	 * Borra observacion si es necesario
	 * @param residencia
	 * @param observacion
	 */
	
	public void updateResidenciaObservacion(Residencias residencia, Residenciasobservaciones observacion) {		
		
		//TODO session.clear();

		// Comienzo de transacción
		session.beginTransaction();
		
		if (residencia.getResidenciasobservaciones() != null) { // Si la residencia tiene vinculada la observacion

			if (observacion.getObservaciones().length() > 1) { // Observacion con contenido
				
				System.out.println(observacion.getObservaciones());
				session.update(residencia);
				session.update(observacion);
			} else {
			
				// Si la observacion no tiene contenido borrarla de la BBDD
				residencia.setResidenciasobservaciones(null);
				session.save(residencia);
				session.delete(observacion);
			}

		}else {		

			// Vincular observación a la residencia
			residencia.setResidenciasobservaciones(observacion);
			observacion.setResidencias(residencia);

			session.saveOrUpdate(residencia);
			session.saveOrUpdate(observacion);

		}

		
		session.getTransaction().commit();

	}
	
	/**
	 * Permite obtener la lista de todas las residencias
	 * @return
	 */
	
	public List<Residencias> selectResidencias() {

		// Comienzo de transacción		
		session.beginTransaction();
		
		Query query = session.createQuery("FROM Residencias");	
		List<Residencias> listaResidencias = query.list();
		
		// Commit de la transacción
		session.getTransaction().commit();
		
		return listaResidencias;
	}
	
	/**
	 * Permite obtener la lista de todas las universidades
	 * @return
	 */
	
	public List<Universidades> selectUniversidades() {
					
		// Comienzo de transacción		
		session.beginTransaction();
		
		Query query = session.createQuery("FROM Universidades");	
		List<Universidades> listUniversidades = query.list();
		
		// Commit de la transacción
		session.getTransaction().commit();
									
				
		return listUniversidades;
	}
	
	/**
	 * Permite obtener una lista de todas las estancias
	 * @return
	 */
	
	public List<Estancias> selectEstancias(){

		// Comienzo de transacción
		session.clear(); //TODO Revisar no deberia ir aqui
		session.beginTransaction();

		Query query = session.createQuery("FROM Estancias");
		List<Estancias> listEstancias = query.list();

		// Commit de la transacción
		session.getTransaction().commit();

		return listEstancias;
		
	}
	
	/**
	 * Permite obtener una listas de todos los estudiantes
	 * @return
	 */
	
	public List<Estudiantes> selectEstudiantes(){
		
		// Comienzo de transacción		
		session.beginTransaction();

		Query query = session.createQuery("FROM Estudiantes");
		List<Estudiantes> listEstudiantes = query.list();

		// Commit de la transacción
		session.getTransaction().commit();

		return listEstudiantes;
	}
	
	
	
	/**
	 * Permite obtener una residencia por su codigo de residencia
	 * usndo una sentencia hql 
	 * @param codResidencia
	 * @return 
	 */
	public Residencias selectResidenciaById(String codResidencia) {
		Query query = session.createQuery("SELECT r FROM Residencias r WHERE r.codResidencia = :codResidencia");
		query.setParameter("codResidencia", codResidencia); 
		Residencias residencia = (Residencias)query.uniqueResult(); 
		
		return residencia;
		
	}
	
	/**
	 * Permite obtener una residencia por su codigo de residencia
	 * usando session.get
	 * @param codResidencia
	 * @return 
	 */	
	public Residencias selectResidenciaById2(String codResidencia) {
					
		Residencias residencia=(Residencias)session.get(Residencias.class,101);
		
		return residencia; 
		
	}
	


}
