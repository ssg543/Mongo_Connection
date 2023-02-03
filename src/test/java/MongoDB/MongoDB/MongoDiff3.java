package MongoDB.MongoDB;
import org.bson.Document;
import org.json.JSONObject;
import java.io.FileReader;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class MongoDiff3 
{
	    public static void main(String[] args) throws Exception {
	        JSONObject json = new JSONObject(new FileReader("C:\\Users\\gopi_s\\RestAssure2\\MongoDB\\src\\test\\java\\MongoDB\\MongoDB\\Mongo.json"));
	        MongoClient mongoClient = MongoClients.create();
	        MongoDatabase database = mongoClient.getDatabase("BFSI_TEST");
	        MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS");
	        Document document = collection.find().first();
	        JSONObject mongoJson = new JSONObject(document.toJson());
	        System.out.println(mongoJson);
	        System.out.println("Something went wrong Below fields are Mismatched: " +"\n");
	        for (String key : json.keySet()) {
	            if (!mongoJson.has(key)) {
	                System.out.println(key);
	            } else if (!json.get(key).equals(mongoJson.get(key))) {
	                System.out.println(key);
	            }
	            else
	            {
	            	System.out.println("Both Json and Mongo Documents are same");
	            }
	        }
	        for (String key : mongoJson.keySet()) {
	            if (!json.has(key)) {
	                System.out.println(key);
	            }
	            else
	            {
	            	System.out.println("Both Json and Mongo Documents are same");
	            }
	        }
	    }
}
