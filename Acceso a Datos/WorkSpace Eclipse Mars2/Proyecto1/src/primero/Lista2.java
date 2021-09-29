package primero;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Lista2 {

	public static void main(String[] args) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Query<Vuelo> q = session.createQuery("from Vuelo as e where e.sucursal.codSucursal = '2lpdas0120'");
			List<Vuelo> lista = q.list();

			Iterator<Vuelo> iter = lista.iterator();
			while (iter.hasNext()) {
				Vuelo vuelo = iter.next();
				System.out.println(vuelo.getNumVuelo() + " // " + vuelo.getOrigen() + " // " + vuelo.getDestino());
			}

			session.close();
			System.exit(0);

		} catch (Exception e) {
			System.out.println("Error no controlado.");
		}

	}

}
