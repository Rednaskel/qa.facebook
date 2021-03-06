package facebooksteps;

import java.util.ArrayList;
import java.util.List;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sourceforge.htmlunit.corejs.javascript.ast.ThrowStatement;
import threading.FacebookPerson;
import threading.SearchThread;

import static org.junit.Assert.fail;


public class ThreadMenager {

	private final int MAX_THREAD_COUNT = 3;
	List<FacebookPerson> friendsChain = new ArrayList<>();
	private static FacebookPerson me = new FacebookPerson("Rednaskel"); //Start person
	
	@Given("^I have my friends list$")
	public void friendListNotEmpty(){
		if(me.getFriendsList() == null){
			fail("Friends list empty");
		}
	}
	@Then("^I get friends list$")
	public void createNewThread(){
		
		List<Thread> threadList = new ArrayList<>();
		threadList.add(new Thread(new SearchThread(me)));
		threadList.get(0).start();
		try {
			threadList.get(0).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@When("^I look for second degree friends$")
	public void I_look_for_second_degree_friends() throws Throwable {
		if(me.getFriendsList() == null){
			throw new Exception("This method should be used with friends list found");
		}
		
		
	}
	
}
