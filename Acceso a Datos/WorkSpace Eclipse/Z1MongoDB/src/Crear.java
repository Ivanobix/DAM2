import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Crear {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("instituto");
		MongoCollection<Document> clnue = db.getCollection("alumnosIvanGarcia");

		Document doc = new Document("nombre", "Iván").append("edad", 19).append("curso", "2DAM")
				.append("modulo", "SistemasInformaticos").append("anyoingreso", 2019);
		clnue.insertOne(doc);
		doc = new Document("nombre", "Lucía").append("edad", 12).append("curso", "1ESO").append("modulo", "Science")
				.append("anyoingreso", 2015);
		clnue.insertOne(doc);
		doc = new Document("nombre", "Julián").append("edad", 45).append("curso", "1ASIR")
				.append("modulo", "AccesoDatos").append("anyoingreso", 2006);
		clnue.insertOne(doc);
		cliente.close();
	}

}
