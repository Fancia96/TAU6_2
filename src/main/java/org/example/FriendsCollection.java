package org.example;

import java.rmi.UnknownHostException;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class FriendsCollection {

    private MongoCollection friends;

    public FriendsCollection() throws UnknownHostException{
        @SuppressWarnings({ "deprecation", "resource" })
        DB db = new MongoClient().getDB("friendships");
        friends = new Jongo(db).getCollection("friends");

        //DB db = new MongoClient(new ServerAddress("mongodb+srv://Fancia:1234@fanciamongo.fs8jv9a.mongodb.net/?retryWrites=true&w=majority")).getDB("friendships");
        //friends = new Jongo(db).getCollection("friends");
    }

    public Person findByName(String name){
        return friends.findOne("{_id: #", name).as(Person.class);
    }

    public void save(Person p){
        friends.save(p);
    }

    public static void main( String args[] ) {

        DB db = new MongoClient().getDB("friendships");
        MongoCollection friends = new Jongo(db).getCollection("friends");
        System.out.println(db); // [journaldev, local, admin]

        //FriendsCollection fr = new FriendsCollection();
        System.out.println(friends);
    }
}
