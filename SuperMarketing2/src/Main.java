import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		UserInput user = new UserInput();
		user.userNameGet();
		
		user.userInput1();
		user.printBill();
		user.saveData();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do u want to proceed For Next transaction,Enter yes or no");
		String response = br.readLine();
		while (response.equalsIgnoreCase("yes")) {
			UserInput user2 = user;
			user2.userNameGet();
		
			user2.userInput1();
			user2.printBill();
			user2.saveData();

			System.out.println("Do u want to proceed For Next transaction,Enter Yes or No");
			String response2 = br.readLine();
			response = response2;
		}
		if (response.equalsIgnoreCase("no")) {
			System.out.println("Ok,U can Login After Sometime");
		}

	}

}
