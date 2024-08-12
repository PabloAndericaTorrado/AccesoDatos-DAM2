package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.Cuidador;
import jakarta.persistence.NoResultException;

public class CuidadorRepository implements Repository<Cuidador> {

	final Session session;
	
	
	
	public CuidadorRepository(Session session) {
		super();
		this.session = session;
	}

	@Override
	public List<Cuidador> findAll() {
		session.beginTransaction();
		Query<Cuidador> query=session.createQuery("FROM Cuidador",Cuidador.class);
		List<Cuidador> personas=query.getResultList();
		session.getTransaction().commit();
		return personas;
	}

	@Override
	public Cuidador findOneById(Long i) {
		session.beginTransaction();
		Query<Cuidador> query=session.createQuery("FROM cuidadores where id=: id",Cuidador.class);
		query.setParameter("id", i);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no se ha podido recuperar esa persona con ese id");
			return new Cuidador();
		}
		finally {
			session.getTransaction().commit();

		}
	}

	@Override
	public void add(Cuidador t) {
		session.beginTransaction();
		session.clear();
		//session.save(persona);
		session.persist(t);
		session.getTransaction().commit();
	    System.out.println("Insert into cuidadores, nombre " + t.getNombre()+ ", apellidos: " + t.getApellidos() + " ,telefono: "+ t.getTelefono());
		
	}

	@Override
	public void updateById(Long id) {
		session.beginTransaction();
		Cuidador cuidador= new Cuidador();
		cuidador.setId(id);
		session.merge(cuidador);
		session.getTransaction().commit();
		
	}

	@Override
	public void updateById(Long id, Cuidador t) {
		session.beginTransaction();
		t.setId(id);
		session.merge(t);
		
	}

	@Override
	public void deleteById(Long id) {
		session.beginTransaction();
		session.clear();
		Cuidador cuidador= new Cuidador();
		cuidador.setId(id);
		session.remove(cuidador);
		session.getTransaction().commit();
		
	}

	public void delete(Cuidador t) {
		session.beginTransaction();
		session.remove(t);
		session.getTransaction().commit();

		
	}

	public void update(Cuidador t) {
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
	}
	
	

}
