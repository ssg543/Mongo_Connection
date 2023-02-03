package MongoDB.MongoDB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
public class SampleMongoDiff 
{
	public static void main(String[] args) throws FileNotFoundException{
		// Connect to MongoDB
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("BFSI_TEST");
		MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS");
		System.out.println("Connectd MongoDB");
		// Read the JSON file
		JSONObject jsonObject = new JSONObject(new FileReader("C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json"));
		System.out.println("File Taken");
		// Find the MongoDB document
		Document mongoDocument = collection.find().first();
		// Compare the JSON data and MongoDB document data
		System.out.println("Started Comparision");
		for (String key : jsonObject.keySet()) {
		Object jsonValue = jsonObject.get(key);
		Object mongoValue = mongoDocument.get(key);
		if (!jsonValue.equals(mongoValue)) {
		System.out.println("Change detected: " + key + " = " + jsonValue + " (JSON) vs " + mongoValue + " (MongoDB)");
		System.out.println("Below are changes");
		}
		}

		// Close the MongoDB connection
		mongoClient.close();
		}
		}

