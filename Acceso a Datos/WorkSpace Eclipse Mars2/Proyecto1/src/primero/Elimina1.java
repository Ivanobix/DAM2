package primero;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class Elimina1 {

	public static void main(String[] args) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

			Vuelo vuelo = session.load(Vuelo.class, 12);

			session.delete(vuelo);
			tx.commit();
			session.close();

			System.out.println("Operación realizada con éxito");
			System.exit(0);

		} catch (ConstraintViolationException e) {
			System.out.println("No puede eliminarse, tiene referencias");
		} catch (org.hibernate.ObjectNotFoundException e) {
			System.out.println("El elemento no existe.");
		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}

	}

}
