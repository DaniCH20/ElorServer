package menu;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import objetos.Alumno;
import objetos.Ciclo;
import objetos.Usuario;

public class Consultas {
	public Usuario obtenerDatosUsuario(String nombre, String pass) {
		Usuario user = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from objetos.Usuario as user where user.nombre = :nombre and user.clave = '"+pass+"'";
		Query q = session.createQuery(hql);

		q.setParameter("nombre", nombre);


		// Usa uniqueResult para obtener un único resultado
		user = (Usuario) q.uniqueResult();

		if (user != null) {
			System.out.println(user.getNombre());
			System.out.println(user.getApellido());
			System.out.println(user.getDni());
			System.out.println(user.getExiste());
			System.out.println(user.getTelefono1());
		} else {
			System.out.println("No se encontró un usuario con esos datos.");
		}

		return user;
	}
	public Alumno obtenerDatosEstudiante(int id) {
		Alumno user = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from objetos.Alumno as user where user.id = :id";
		Query q = session.createQuery(hql);

		q.setParameter("id", id);


		// Usa uniqueResult para obtener un único resultado
		user = (Alumno) q.uniqueResult();

		return user;
	}
	public Ciclo obtenerDatosCiclo(int id) {
		Ciclo user = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from objetos.Ciclo as C where C.id= :id";
		Query q = session.createQuery(hql);

		q.setParameter("id", id);


		// Usa uniqueResult para obtener un único resultado
		user = (Ciclo) q.uniqueResult();

		return user;
	}

	public Boolean actualizarUsuario(int id,String apellido , String clave,String direccion,String dni,String foto,String login , String nombre,
			int telefono , int telefono2) {
		Usuario user = null;
		Boolean respuesta=false;
		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();
		String hql = "from objetos.Usuario as user where user.id = :id ";
		Query q = session.createQuery(hql);

		q.setParameter("id", id);

		// Usa uniqueResult para obtener un único resultado
		user = (Usuario) q.uniqueResult();
		
		if (user != null) {
			System.out.println(user.getNombre());
			System.out.println(user.getApellido());
			System.out.println(user.getDni());
			
			user.setApellido(apellido);
			user.setClave(clave);
			user.setDireccion(direccion);
			user.setDni(dni);
			//user.setFoto(foto);
			user.setLogin(login);
			user.setNombre(nombre);
			user.setTelefono1(telefono);
			user.setTelefono2(telefono2);
			user.setExiste(true);
			session.save(user);
			respuesta=true;
			tx.commit();
		} else {
			System.out.println("No se encontró un usuario con esos datos.");
			respuesta=false;
		}
		
		return respuesta;
	}
	public Boolean actualizarClaveUsuario(String nombre, String clave) {
		Usuario user = null;
		Boolean respuesta=false;
		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();
		String hql = "from objetos.Usuario as user where user.nombre = :nombre ";
		Query q = session.createQuery(hql);

		q.setParameter("nombre", nombre);

		// Usa uniqueResult para obtener un único resultado
		user = (Usuario) q.uniqueResult();
		
		if (user != null) {
			System.out.println(user.getNombre());
			System.out.println(user.getApellido());
			
			user.setClave(clave);
			session.save(user);
			tx.commit();
			respuesta=true;
		} else {
			System.out.println("No se encontró un usuario con esos datos.");
			respuesta=false;
		}
		
		return respuesta;
	}
	public Usuario verificarUsuario(String nombre, String clave) {
		Usuario user = null;
		Boolean verificar=false;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction transaction = session.beginTransaction();
		String hql = "from objetos.Usuario as user where user.nombre = :nombre and user.clave = :pass";
		Query query = session.createQuery(hql);

		query.setParameter("nombre", nombre);
		query.setParameter("pass", clave);
		
		user = (Usuario) query.uniqueResult();

		return user;
	}
	public Usuario existeUsuario(String nombre) {
		Usuario user = null;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction transaction = session.beginTransaction();
		String hql = "from objetos.Usuario as user where  user.nombre = :nombre";
		Query query = session.createQuery(hql);

		query.setParameter("nombre", nombre);
		
		user = (Usuario) query.uniqueResult();
		

		return user;
	}
	public boolean actualizarPassword(String nombre, String nuevaContraseña) {
        Usuario user = null;
        Boolean respuesta=false;
        SessionFactory sesion = HibernateUtil.getSessionFactory();

        Session session = sesion.openSession();

        Transaction tx = session.beginTransaction();
        String hql = "from objetos.Usuario as user where user.nombre = :nombre ";
        Query q = session.createQuery(hql);

        q.setParameter("nombre", nombre);

        // Usa uniqueResult para obtener un único resultado
        user = (Usuario) q.uniqueResult();
        
        if (user != null) {
            System.out.println(user.getNombre());
            System.out.println(user.getApellido());
            
            user.setClave(nuevaContraseña);
            session.save(user);
            tx.commit();
            respuesta=true;
        } else {
            System.out.println("No se encontró un usuario con esos datos.");
            respuesta=false;
        }
        
        return respuesta;
    }
	
	public Usuario UsuarioDeAlta(String nombre) {
        Usuario user = null;

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();

        Transaction transaction = session.beginTransaction();
        String hql = "from objetos.Usuario as user where user.nombre = :nombre and user.existe = true";
        Query query = session.createQuery(hql);

        query.setParameter("nombre", nombre);

        user = (Usuario) query.uniqueResult();

        transaction.commit();
        session.close();

        return user;
    }
}
