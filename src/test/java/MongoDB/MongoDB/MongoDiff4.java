package MongoDB.MongoDB;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDiff4 {

	public static void main(String[] args) throws Exception {
		// Read JSON file
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json"));
		Map<String, Object> jsonMap = jsonObject;
		// Connect to MongoDB and retrieve document
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("BFSI_TEST1");
		MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS1");
		FindIterable<Document> iterable = collection.find();
		for (Document document : iterable) {
//			System.out.println("Mongo Document: " + document);
			Map<String, Object> mongoMap = new HashMap<>(document);
//			System.out.println("JSON Map: " + jsonMap);
//			System.out.println("Mongo Map: " + mongoMap);
			// Compare the two maps and print differences
			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				String key = entry.getKey();
				Object jsonValue = entry.getValue();
				Object mongoValue = mongoMap.get(key);
//				System.out.println("**Comparing: ** " +"\n"+"\n"+ "FieldName : "+key + "\n"+"\n"+ "JSON: " + jsonValue +"\n"+ "Mongo: " + mongoValue);
				if (!jsonValue.equals(mongoValue)) {
					System.out.println("**Difference in below field name: **" +"\n"+"FieldName : "+ key +"\n"+ "JSON      : " + jsonValue +"\n"+ "MONGO     : " + mongoValue +"\n");
				}
				else
				{
//					System.out.println("Both Files Looks Same :) ");
				}
			}
		}
		mongoClient.close();
	}
}
