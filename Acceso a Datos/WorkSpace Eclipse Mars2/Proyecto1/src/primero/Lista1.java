package primero;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Lista1 {

	public static void main(String[] args) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Query<Sucursal> q = session.createQuery("from Sucursal");
			List<Sucursal> lista = q.list();

			Iterator<Sucursal> iter = lista.iterator();
			while (iter.hasNext()) {
				Sucursal sucursal = iter.next();
				System.out.println(
						sucursal.getCodSucursal() + " // " + sucursal.getDireccion() + " // " + sucursal.getDirector());
			}

			session.close();
			System.exit(0);

		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}

	}

}
