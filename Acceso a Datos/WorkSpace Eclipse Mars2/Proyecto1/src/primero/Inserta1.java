package primero;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class Inserta1 {

	public static void main(String[] args) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

			Sucursal suc = new Sucursal();
			suc.setCodSucursal("2lpdas0120");
			suc.setDirector("Marta Garrido Vega");
			suc.setNumTrabajadores(578);
			suc.setDireccion("Av Lancia 89");
			suc.setTelefono("987360183");

			session.save(suc);
			tx.commit();
			session.close();

			System.out.println("Operación realizada con éxito");
			System.exit(0);

		} catch (ConstraintViolationException e) {
			System.out.println("Elemento duplicado.");
		} catch (TransientPropertyValueException e) {
			System.out.println("El elemento no existe.");
		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}
	}

}
