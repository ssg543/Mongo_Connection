package MongoDB.MongoDB;

	import com.mongodb.client.MongoClient;
	import com.mongodb.client.MongoClients;
	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoDatabase;
	import org.bson.Document;

	import java.io.File;
	import java.io.IOException;
	import java.nio.file.Files;

	public class MongoFileSent 
	{
	public static void main(String[] args) throws IOException {
	// Connect to the MongoDB server
	MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

	// Get a reference to the database
	MongoDatabase database = mongoClient.getDatabase("BFSI_TEST1");

	// Get a reference to the collection
	MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS1");

	// Read the contents of the JSON file into a string
	File jsonFile = new File("C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json");
	String jsonString = new String(Files.readAllBytes(jsonFile.toPath()));

	// Parse the JSON string into a Document
	Document doc = Document.parse(jsonString);

	// Insert the Document into the collection
	collection.insertOne(doc);
	System.out.println("File Added Successfully  :)");
	}
	}

