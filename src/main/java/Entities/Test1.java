package Entities;

import java.util.Scanner;
import java.security.PublicKey;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import Repository.CuidadorRepository;
import Repository.PropietarioRepository;
import Repository.MascotaRepository;

public class Test1 {
	@SuppressWarnings("deprecation")
	private static Scanner teclado;
	private static CuidadorRepository cuidadorRepository;
	private static PropietarioRepository propietarioRepository;
	private static MascotaRepository mascotaRepository;
    
	public static void main(String[] args) {
		
		System.out.println("Leyendo la configuración del fichero hibernate.cfg.xml");
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
		final SessionFactory factory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
		System.out.println("Conectando a la BBDD");
		final Session session = factory.openSession();
		
		cuidadorRepository = new CuidadorRepository(session);
		propietarioRepository = new PropietarioRepository(session);
		mascotaRepository = new MascotaRepository(session);
		
		Cuidador cuidador1 = new Cuidador("Antonio","Gonzalez Suarez","111111111");
		Cuidador cuidador2 = new Cuidador("Maria","Perez Lopez","222222222");
		
		cuidadorRepository.add(cuidador1);
		cuidadorRepository.add(cuidador2);		//Pablo Anderica Torrado
		
		Propietario propietario1 = new Propietario("Luisa","Suarez Mochon","333333333");
		Propietario propietario2 = new Propietario("Jaime","Delgado Gil","444444444");
		
		propietarioRepository.add(propietario1);
		propietarioRepository.add(propietario2);
		
		Mascota mascota1 = new Mascota("Bombon","Pequines");
		Mascota mascota2 = new Mascota("Yakie","Podenco");
		Mascota mascota3 = new Mascota("Leon","Pastor aleman");
		
		mascota1.setPropietario(propietario1);
		mascota1.setCuidador(cuidador1);
		mascota2.setPropietario(propietario1);
		mascota2.setCuidador(cuidador2);
		mascota3.setPropietario(propietario2);
		mascota3.setCuidador(cuidador1);

		mascotaRepository.add(mascota1);
		mascotaRepository.add(mascota2);
		mascotaRepository.add(mascota3);
		
		listCuidadores();
		propietarioRepository.findAll().forEach(p -> System.out.println(p));
        System.out.println(propietarioRepository.findOneByNameApellidos("Luisa", "Suárez Mochón").toString());
		propietarioRepository.listPropietarios().forEach(p -> System.out.println(p));

		mostrarMenu();
		
    }
       
	public static List<Cuidador> listCuidadores() {
	    List<Cuidador> cuidadores = cuidadorRepository.findAll();

	    for (Cuidador cuidador : cuidadores) {
	    	System.out.println("--------------------");
	        System.out.println("ID: " + cuidador.getId());
	        System.out.println("Nombre: " + cuidador.getNombre());
	        System.out.println("Apellidos: " + cuidador.getApellidos());
	        System.out.println("Teléfono: " + cuidador.getTelefono());
	        System.out.println("--------------------");
	    }

	    return cuidadores;
	}
	
	private static void mostrarMenu() {
	    Scanner scanner = new Scanner(System.in);
	    int opcion;
	    
	    do {
	        System.out.println("Por favor, introduce la opción deseada:");
	        System.out.println("1.- Crear nuevo cuidador");
	        System.out.println("2.- Crear nuevo propietario");
	        System.out.println("3.- Crear nueva mascota");
	        System.out.println("4.- Listar todos los propietarios");
	        System.out.println("5.- Listar todos los cuidadores");
	        System.out.println("6.- Listar todas las mascotas");
	        System.out.println("7.- Eliminar propietario por ID");
	        System.out.println("8.- Eliminar cuidador por ID");
	        System.out.println("9.- Eliminar mascota por ID");
	        System.out.println("0.- Salir");
	        
	        opcion = scanner.nextInt();
	        switch (opcion) {
	            case 0:
	                System.out.println("Gracias por usar la aplicación");
	                break;
	            case 1:
	                Cuidador nuevoCuidador = new Cuidador();
	                System.out.println("Inserta el nombre del cuidador:");
	                String nombreCuidador = scanner.next();
	                System.out.println("Inserta el apellido del cuidador:");
	                String apellidoCuidador = scanner.next();
	                System.out.println("Inserta el número de teléfono:");
	                String telefonoCuidador = scanner.next();
	                nuevoCuidador.setNombre(nombreCuidador);
	                nuevoCuidador.setApellidos(apellidoCuidador);
	                nuevoCuidador.setTelefono(telefonoCuidador);
	                cuidadorRepository.add(nuevoCuidador);
	                break;
	            case 2:
	                Propietario nuevoPropietario = new Propietario();
	                System.out.println("Inserta el nombre del propietario:");
	                String nombrePropietario = scanner.next();
	                System.out.println("Inserta el apellido del propietario:");
	                String apellidoPropietario = scanner.next();
	                System.out.println("Inserta el número de teléfono:");
	                String telefonoPropietario = scanner.next();
	                nuevoPropietario.setNombre(nombrePropietario);
	                nuevoPropietario.setApellidos(apellidoPropietario);
	                nuevoPropietario.setTelefono(telefonoPropietario);
	                propietarioRepository.add(nuevoPropietario);
	                break;
	            case 3:
	                Mascota nuevaMascota = new Mascota();
	                System.out.println("Inserta el nombre de la mascota:");
	                String nombreMascota = scanner.next();
	                System.out.println("Inserta la raza de la mascota:");
	                String razaMascota = scanner.next();
	                nuevaMascota.setNombre(nombreMascota);
	                nuevaMascota.setRaza(razaMascota);
	               
	                mascotaRepository.add(nuevaMascota);
	                break;
	            case 4:
	                propietarioRepository.findAll().forEach(p -> System.out.println(p));
	                break;
	            case 5:
	                cuidadorRepository.findAll().forEach(c -> System.out.println(c));
	                break;
	            case 6:
	                mascotaRepository.findAll().forEach(m -> System.out.println(m));
	                break;
	            case 7:
	                System.out.println("Inserta el ID del propietario que quieres borrar:");
	                Long idPropietario = scanner.nextLong();
	                propietarioRepository.deleteById(idPropietario);
	                break;
	            case 8:
	                System.out.println("Inserta el ID del cuidador que quieres borrar:");
	                Long idCuidador = scanner.nextLong();
	                cuidadorRepository.deleteById(idCuidador);
	                break;
	            case 9:
	                System.out.println("Inserta el ID de la mascota que quieres borrar:");
	                Long idMascota = scanner.nextLong();
	                mascotaRepository.deleteById(idMascota);
	                break;
	        }
	    } while (opcion != 0);
	}

	
	
	
	
	
	


	

}
