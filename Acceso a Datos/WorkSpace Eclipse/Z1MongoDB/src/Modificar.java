import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Modificar {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("instituto");
		MongoCollection<Document> coleccion = db.getCollection("alumnosIvanGarcia");

		coleccion.updateMany(exists("_id"), set("IES", "San Andrés del Rabanedo"));
		cliente.close();
	}

}
