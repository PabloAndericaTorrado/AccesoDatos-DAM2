package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.Cuidador;
import Entities.Mascota;
import jakarta.persistence.NoResultException;

public class MascotaRepository implements Repository<Mascota> {

final Session session;
	
	
	
	public MascotaRepository(Session session) {
		this.session = session;
	}

	@Override
	public List<Mascota> findAll() {
		session.beginTransaction();
		Query<Mascota> query=session.createQuery("FROM mascotas",Mascota.class);
		List<Mascota> mascotas=query.getResultList();
		session.getTransaction().commit();
		return mascotas;
	}

	@Override
	public Mascota findOneById(Long i) {
		session.beginTransaction();
		Query<Mascota> query=session.createQuery("FROM mascotas where id=: id",Mascota.class);
		query.setParameter("id", i);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no se ha podido recuperar esa mascota con ese id");
			return new Mascota();
		}
		finally {
			session.getTransaction().commit();

		}
	}

	@Override
	public void add(Mascota t) {
		session.beginTransaction();
		session.clear();
		
		session.persist(t);
		session.getTransaction().commit();
	    System.out.println("Insert into mascotas, id " + t.getId()+ ", nombre: " + t.getNombre() + " ,raza: "+ t.getRaza()+ ", id_Propietario: " + t.getPropietario()+ ", id_cuidador: " + t.getCuidador());
		
	}

	@Override
	public void updateById(Long id) {
		session.beginTransaction();
		Mascota mascota= new Mascota();
		mascota.setId(id);
		session.merge(mascota);
		session.getTransaction().commit();
		
	}

	@Override
	public void updateById(Long id, Mascota t) {
		session.beginTransaction();
		t.setId(id);
		session.merge(t);
		
	}

	@Override
	public void deleteById(Long id) {
		session.beginTransaction();
		session.clear();
		Mascota mascota= new Mascota();
		mascota.setId(id);
		session.remove(mascota);
		session.getTransaction().commit();
		
	}

	public void delete(Mascota t) {
		session.beginTransaction();
		session.remove(t);
		session.getTransaction().commit();

		
	}

	public void update(Mascota t) {
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
	}

}
