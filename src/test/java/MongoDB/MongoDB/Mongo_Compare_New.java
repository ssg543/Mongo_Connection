package MongoDB.MongoDB;

	import com.mongodb.client.MongoClient;
	import com.mongodb.client.MongoClients;
	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.model.Filters;
	import org.bson.Document;
	import org.bson.json.JsonMode;
	import org.bson.json.JsonWriterSettings;
	
	public class Mongo_Compare_New {
	public static void main(String[] args) {
	MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
	MongoCollection<Document> collection = mongoClient.getDatabase("BFSI_TEST").getCollection("BFSI_DETAILS");
	Document doc = collection.find(Filters.eq("_id", "some-id")).first();
	String json = doc.toJson(new JsonWriterSettings(JsonMode.SHELL, false));

	System.out.println("MongoDB document: " + doc);
	System.out.println("JSON: " + json);
	System.out.println("Difference: " + findDifference(doc, json));
	}

	private static String findDifference(Document doc, String json) 
	{
		return findDifference(doc, json);
	// Your implementation to compare the Document and JSON and return the difference
	}
	}