import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Updates.inc;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Incrementar {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("instituto");
		MongoCollection<Document> coleccion = db.getCollection("alumnosIvanGarcia");

		int edadActual;
		int edadASumar;
		MongoCursor<Document> docs = coleccion.find(lt("edad", 17)).iterator();
		while (docs.hasNext()) {
			Document doc = docs.next();
			edadActual = doc.getInteger("edad", 0);
			edadASumar = 18 - edadActual;
			coleccion.updateOne(eq("_id", doc.getObjectId("_id")), inc("edad", edadASumar));
		}
		cliente.close();
	}

}
