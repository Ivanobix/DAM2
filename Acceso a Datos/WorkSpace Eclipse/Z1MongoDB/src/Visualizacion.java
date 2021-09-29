import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Visualizacion {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("instituto");
		MongoCollection<Document> coleccion = db.getCollection("alumnosIvanGarcia");

		System.out.println("----- BASES DE DATOS -----");
		for (String baseDatos : cliente.listDatabaseNames()) {
			System.out.println(baseDatos);
		}

		System.out.println("----- COLECCIONES (INSTITUTO) -----");
		for (String colect : db.listCollectionNames()) {
			System.out.println(colect);
		}

		System.out.println("----- DOCUMENTOS (ALUMNOS) -----");
		ArrayList<Document> documentos = coleccion.find().into(new ArrayList<>());
		for (Document documento : documentos) {
			System.out.println(documento.toString());
		}
		cliente.close();
	}

}
