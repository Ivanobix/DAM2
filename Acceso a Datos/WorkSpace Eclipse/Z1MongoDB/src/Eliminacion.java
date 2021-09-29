import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Eliminacion {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("instituto");
		MongoCollection<Document> coleccion = db.getCollection("alumnosIvanGarcia");

		coleccion.deleteMany(eq("modulo", "BasesDatos"));
		cliente.close();
	}

}
