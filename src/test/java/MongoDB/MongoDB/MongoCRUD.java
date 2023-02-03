package MongoDB.MongoDB;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoCRUD 
{
	public static void main(String[] args)
	{
		// Creating a Mongo client 
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
		System.out.println("Created Mongo Connection successfully"); 
		
		MongoDatabase db = mongoClient.getDatabase("BFSI_TEST");
		System.out.println("Get database is successful");
		
		//creating collection or get collection if exists.
		MongoCollection<Document>  collection= db.getCollection("BFSI_DETAILS");
		System.out.println("collection created ");
		
		//Inserting sample records by creating documents.
		Document doc =new Document("name","BANK_DETAILS");
		doc.append("id",101);  
		doc.append("Name","CITI");  
		doc.append("nationalId", "045321567");
		doc.append("serviceId", "FEDWIRE");
		doc.append("preferenceBankFlag", false);
		doc.append("overrideflag", true);
		doc.append("sourceModifiedUser", "GUSUSER");
		doc.append("wfFlag", false);
		collection.insertOne(doc);
		System.out.println("Insert1 is completed");
		 
		Document doc2 =new Document("name","USER_DETAILS");
		doc2.append("id",102);  
		doc2.append("Name","BOA");  
		doc2.append("nationalId", "545321567");
		doc2.append("serviceId", "SWIFT");
		doc2.append("preferenceBankFlag", false);
		doc2.append("overrideflag", true);
		doc2.append("sourceModifiedUser", "GUSUSER");
		doc2.append("wfFlag", false);
		collection.insertOne(doc2);
		System.out.println("Insert2 is completed"); 
		
		//Listing All Mongo Documents in Collection
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;
		// Getting the iterator
		System.out.println("Listing All Mongo Documents");
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
		    System.out.println(it.next());
		    i++; 
		}
		//specific document retrieving in a collection
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "USER_DETAILS");
		System.out.println("Retrieving specific Mongo Document");
		MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
		while (cursor.hasNext()) {
		    System.out.println(cursor.next());
		}
		
		collection.updateOne(Filters.eq("name", "USER_DETAILS"), Updates.set("id", "108"));
		
		FindIterable<Document> iterDoc1 = collection.find();
		int j = 1;
		// Getting the iterator
		System.out.println("Listing All Mongo Documents");
		Iterator it1 = iterDoc1.iterator();
		while (it1.hasNext()) {
		    System.out.println(it1.next());
		    j++; 
		}
		
	}
}