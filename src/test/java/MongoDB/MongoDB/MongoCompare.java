package MongoDB.MongoDB;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonValue;

import org.bson.Document;

import com.fasterxml.jackson.core.type.TypeReference;
//import com.google.diffmatchpatch.DiffMatchPatch;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoCompare {
	public static void main(String[] args) throws IOException {
		// Connect to the MongoDB server
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

		// Get a reference to the database
		MongoDatabase database = mongoClient.getDatabase("BFSI_TEST1");

		// Get a reference to the collection
		MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS1");

		// Read the contents of the JSON file into a string
		File jsonFile = new File(
				"C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json");
		String jsonString = new String(Files.readAllBytes(jsonFile.toPath()));

		// Parse the JSON string into a Document
		Document fileDoc = Document.parse(jsonString);

		// Get all documents from the collection
		List<Document> docs = new ArrayList<Document>();
		collection.find().into(docs);
//	System.out.println(collection.find().into(docs));

		// Compare the data in the file to the data in the collection
		
		boolean matchFound = false;
		for (Document dbDoc : docs) {
			if (fileDoc.equals(dbDoc)) {
				matchFound = true;
				break;
			}
		}

		if (matchFound) {
			System.out.println("Data in file matches data in MongoDB");
		} else {
			System.out.println("Data in file does not match data in MongoDB");
		}
		System.out.println(matchFound);
	}
	
}


