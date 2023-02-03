package MongoDB.MongoDB;

import java.io.File;
import java.util.Map;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDiff1
{
	public static void main(String[] args) throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(new File("C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json"));

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("BFSI_TEST1");
		MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS1");

		Document mongoDoc = collection.find().first();

		Map<String, Object> jsonMap = mapper.convertValue(jsonNode, Map.class);
		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
		String key = entry.getKey();
		Object value = entry.getValue();
		if (!mongoDoc.containsKey(key) || !mongoDoc.get(key).equals(value)) {
		System.out.println("Difference found ---> : key=" + key + ", jsonValue=" + value + ", mongoValue=" + mongoDoc.get(key) + "\n");
		}
		}
		}
		}