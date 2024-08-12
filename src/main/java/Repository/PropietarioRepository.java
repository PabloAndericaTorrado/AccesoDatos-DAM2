package Repository;

import java.util.Collections;
import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.Propietario;
import jakarta.persistence.NoResultException;

public class PropietarioRepository implements Repository<Propietario> {

final Session session;
	
	
	
	public PropietarioRepository(Session session) {
		this.session = session;
	}

	@Override
	public List<Propietario> findAll() {
		session.beginTransaction();
		Query<Propietario> query=session.createQuery("FROM Propietario",Propietario.class);
		List<Propietario> personas=query.getResultList();
		session.getTransaction().commit();
		return personas;
	}

	@Override
	public Propietario findOneById(Long i) {
		session.beginTransaction();
		Query<Propietario> query=session.createQuery("FROM propietarios where id=: id",Propietario.class);
		query.setParameter("id", i);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no se ha podido recuperar esa persona con ese id");
			return new Propietario();
		}
		finally {
			session.getTransaction().commit();

		}
	}

	@Override
	public void add(Propietario t) {
		session.beginTransaction();
		session.clear();
		session.persist(t);
		session.getTransaction().commit();
	    System.out.println("Insert into propietarios, nombre " + t.getNombre()+ ", apellidos: " + t.getApellidos() + " ,telefono: "+ t.getTelefono());
		
	}

	@Override
	public void updateById(Long id) {
		session.beginTransaction();
		Propietario propietario= new Propietario();
		propietario.setId(id);
		session.merge(propietario);
		session.getTransaction().commit();
		
	}

	@Override
	public void updateById(Long id, Propietario t) {
		session.beginTransaction();
		t.setId(id);
		session.merge(t);
		
	}

	@Override
	public void deleteById(Long id) {
		session.beginTransaction();
		session.clear();
		Propietario propietario= new Propietario();
		propietario.setId(id);
		session.remove(propietario);
		session.getTransaction().commit();
		
	}

	public void delete(Propietario t) {
		session.beginTransaction();
		session.remove(t);
		session.getTransaction().commit();

	}

	public void update(Propietario t) {
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
	}
	
	public Propietario findOneByNameApellidos(String nombre, String apellidos) {
	    session.beginTransaction();
	    Query<Propietario> query = session.createQuery("FROM Propietario where nombre=:nombre AND apellidos=:apellidos", Propietario.class);
	    query.setParameter("nombre", nombre);
	    query.setParameter("apellidos", apellidos);

	    Propietario propietario = null;
	    try {
	        propietario = query.getSingleResult();
	    } catch (NonUniqueResultException e) {
	        System.out.println("Se encontraron múltiples propietarios con el nombre '" + nombre + "' y apellidos '" + apellidos + "'");
	    } finally {
	        session.getTransaction().commit();
	    }

	    return propietario != null ? propietario : new Propietario();
	}
	
	public List<Propietario> listPropietarios() {
	    session.beginTransaction();

	    try {
	        Query<Propietario> query = session.createQuery(
	                "SELECT DISTINCT p FROM Propietario p " +
	                "LEFT JOIN FETCH p.mascotas m " +
	                "LEFT JOIN FETCH m.cuidador",
	                Propietario.class
	        );

	        List<Propietario> propietarios = query.getResultList();

	        session.getTransaction().commit();

	        return propietarios;
	    } catch (Exception e) {
	        if (session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}


	


	
}
