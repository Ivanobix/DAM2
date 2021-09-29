package primero;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class Inserta2 {

	public static void main(String[] args) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

			Vuelo vul = new Vuelo();
			vul.setNumVuelo(12);
			vul.setFecha(java.sql.Date.valueOf("2021-12-06"));
			vul.setHora(java.sql.Time.valueOf("12:12:12"));

			vul.setOrigen("León");
			vul.setDestino("La Bañeza");
			vul.setPlazasTotales(200);
			vul.setPlazasTuristas(20);

			Sucursal suc = new Sucursal();
			suc.setCodSucursal("2lpdas0120");
			vul.setSucursal(suc);

			session.save(vul);
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
