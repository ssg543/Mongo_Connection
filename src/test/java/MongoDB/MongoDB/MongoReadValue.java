package MongoDB.MongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoReadValue 
{
	public static void main(String[] args) 
	{
        // Connect to the MongoDB server
		MongoClient mongoClient = new MongoClient( "localhost", 27017 ); 
        // Get a reference to the database
        MongoDatabase database = mongoClient.getDatabase("BFSI_TEST");
        // Get a reference to the collection
        MongoCollection<Document> collection = database.getCollection("BFSI_DETAILS");
        // Query for a specific document
        Document myDoc = collection.find(new Document("name", "BANK_DETAILS")).first();
        // Extract the desired value from the document
        Object myValue = myDoc.get("name");
        Document myDoc1 = collection.find(new Document("id", 101)).first();
        // Extract the desired value from the document
        Object myValue1 = myDoc1.get("id");
        
        System.out.println("Value fetched " + myValue1);
        
        // Check the data type of the value
        if (myValue1 instanceof String) {
        System.out.println("Value is a String");
        } else if (myValue1 instanceof Integer) {
        System.out.println("Value is an Integer");
        } else if (myValue1 instanceof Double) {
        System.out.println("Value is a Double");
        } // Add more checks for other data types as needed
        }
}



    