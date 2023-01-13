package org.example;

import java.rmi.UnknownHostException;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class FriendsCollection{

    //private static MongoCollection friends;
    private Multimap<String, Person> friends = ArrayListMultimap.create();

    public FriendsCollection() throws UnknownHostException{
//        @SuppressWarnings({ "deprecation", "resource" })
//        DB db = new MongoClient().getDB("friendships");
//        friends = new Jongo(db).getCollection("friends");
        friends = ArrayListMultimap.create();
    }

    public Person findByName(String name){
        //return friends.findOne("{_id: #", name).as(Person.class);
        List<Person> coll = friends.get(name).stream().toList();

        Person person;
        if (coll.size() == 1)
            return coll.get(0);
        else
            return null;
    }

    public void save(Person p){
        //friends.save(p);
        friends.put(p.getName(), p);
    }

}
