
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.example.Car;
import org.example.FriendsCollection;
import org.example.FriendshipsMongo;
import org.example.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.rmi.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoEasyMockTest {

//	@TestSubject
//	FriendshipsMongo friendships = new FriendshipsMongo();

//	private FriendshipsMongo friendships = EasyMock.createMock(FriendshipsMongo.class);


	//A nice mock expects recorded calls in any order and returning null for other calls
//	@Mock(type = MockType.DEFAULT)
//	FriendsCollection friends;

//	private FriendsCollection friends = EasyMock.createMock(FriendsCollection.class);
//
	//private Person person = EasyMock.createMock(Person.class);

	@Test
	public void mockingWorksAsExpected(){
		//FriendshipsMongo friendships = new FriendshipsMongo();
		Person joe = new Person("Joe");
		//Zapisanie zachowania - co sie ma stac
//		expect(friends.findByName("Joe")).andReturn(joe);
//		//Odpalenie obiektu do sprawdzenia zachowania
//		replay(friends);
//		assertThat(friends.findByName("Joe")).isEqualTo(joe);
	}
	
	@Test
	public void alexDoesNotHaveFriends(){
		assertThat(friendships.getFriendsList("Alex")).isNull();
	}


	FriendsCollection friends = EasyMock.createMock(FriendsCollection.class);
	FriendshipsMongo friendships = EasyMock.createMock(FriendshipsMongo.class);
	Person joe = createMock(Person.class);
	@Test
	public void joeHas5Friends(){

		List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});


		joe.setFriends(expected);
		expect(friends.findByName("Joe")).andReturn(joe);
		expect(joe.getFriends()).andReturn(expected);
		replay(friends);
		replay(joe);
		expect(friendships.getFriendsList("Joe")).andReturn(Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"}));
		assertThat(friendships.getFriendsList("Joe") == null);
	}

	@Test
	void karolHasNoFriends() {
		assertThat(friendships.getFriendsList("Karol")).isNull();
	}

	@Test
	void shouldHaveFriends() {
		Person john = new Person("Maciej");
		john.addFriend("Adam");
		john.addFriend("Dawid");
		john.addFriend("Tomek");

		friendships.makeFriends("Maciej", "Adam");
		friendships.makeFriends("Maciej", "Dawid");
		friendships.makeFriends("Maciej", "Tomek");
		replay(friendships);

		assertThat(john.getFriends().contains("Dawid"));
	}

	@Test
	void shouldBeFriends() {
		Person john = new Person("John");
		john.addFriend("Alice");

		friendships.makeFriends("John", "Alice");
		replay(friendships);


		assertTrue(john.getFriends().contains("Alice"));
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
