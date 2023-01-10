
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.example.FriendsCollection;
import org.example.FriendshipsMongo;
import org.example.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.rmi.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoEasyMockTest {

	@TestSubject
	FriendshipsMongo friendships = new FriendshipsMongo();

	//A nice mock expects recorded calls in any order and returning null for other calls
	@Mock(type = MockType.NICE)
	FriendsCollection friends;


	@Test
	public void mockingWorksAsExpected(){
		Person joe = new Person("Joe");
		//Zapisanie zachowania - co sie ma stac
		expect(friends.findByName("Joe")).andReturn(joe);
		//Odpalenie obiektu do sprawdzenia zachowania
		replay(friends);
		assertThat(friends.findByName("Joe")).isEqualTo(joe);
	}
	
	@Test
	public void alexDoesNotHaveFriends(){
		assertThat(friendships.getFriendsList("Alex")).isEmpty();
	}
	
	@Test
	public void joeHas5Friends(){
		List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
		Person joe = createMock(Person.class);
		expect(friends.findByName("Joe")).andReturn(joe);
		expect(joe.getFriends()).andReturn(expected);
		replay(friends);
		replay(joe);
		assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
	}

	@Test
	void karolHasNoFriends() {
		assertThat(friendships.getFriendsList("Karol")).isEmpty();
	}

	@Test
	void shouldHaveFriends() {
		Person john = new Person("Maciej");
		john.addFriend("Adam");
		john.addFriend("Dawid");
		john.addFriend("Tomek");

		expect(friends.findByName("Maciej")).andReturn(john);
		replay(friends);

		assertThat(friendships.getFriendsList("Maciej"))
				.hasSize(3)
				.containsOnly("Adam", "Dawid", "Tomek");
	}

	@Test
	void shouldBeFriends() {
		Person john = new Person("John");
		john.addFriend("Alice");

		expect(friends.findByName("John")).andReturn(john);
		replay(friends);

		assertTrue(friendships.areFriends("John", "Alice"));
	}

	@Test
	void shouldNotBeFriends() {
		Person john = new Person("John");
		john.addFriend("Alice");

		expect(friends.findByName("John")).andReturn(john);
		replay(friends);

		assertFalse(friendships.areFriends("John", "Bob"));
	}
	
}
