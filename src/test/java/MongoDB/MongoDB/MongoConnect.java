package MongoDB.MongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoConnect {
	public static void main(String[] args) {

		// connect to a MongoDB server.

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Created Mongo Connection Successfully");

		// create DB

		MongoDatabase db = mongoClient.getDatabase("EPE");
		System.out.println("Get database is successful");

		// To get all database names

		System.out.println("Below are list of databases present in MongoDB");
		MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
		while (dbsCursor.hasNext()) {
			System.out.println(dbsCursor.next());
		}

		// Inserting sample record by creating collection and document.
		
		MongoCollection<Document> collection = db.getCollection("EPE Details");
		Document doc = new Document("name", "bankDetails");
		collection.insertOne(doc);
		System.out.println("########### Insert is completed  ###############");
		
		// list all databases
		
		MongoCursor<String> dbsCursor2 = mongoClient.listDatabaseNames().iterator();
		while (dbsCursor2.hasNext()) {
			System.out.println(dbsCursor2.next());
		}

/*		// Drop any Database:
		
		mongoClient.dropDatabase("EPE");
		System.out.println("################## Database dropped successfully ##################");
		System.out.println("After Database getting dropped, present list of Database's...");

		// list all databases
				
		MongoCursor<String> dbsCursor3 = mongoClient.listDatabaseNames().iterator();
		while (dbsCursor3.hasNext()) {
			System.out.println(dbsCursor3.next());
		} */
	}
}
