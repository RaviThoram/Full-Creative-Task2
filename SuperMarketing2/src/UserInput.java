import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserInput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> products = new ArrayList<>();
	ArrayList<Integer> productCost = new ArrayList<>();
	ArrayList<Integer> totalCost = new ArrayList<>();
	ArrayList<String> namesTrack = new ArrayList<>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int totalBill = 0;
	int billOfCurrentItem;
	int noOfItems;
	String nameOfCustomer;
	String userWantSerialize;

	public UserInput() {
		products.add("carrots");
		products.add("tomatos");
		products.add("onions");
		products.add("potatos");

	}

	public void userNameGet() throws IOException {
		System.out.println("Welcome To Vegetable Super Market");

		System.out.println("Please Say Your Name");

		nameOfCustomer = br.readLine();

		gettingUserData(nameOfCustomer);
		namesTrack.add(nameOfCustomer);

	}

	public void gettingUserData(String nameOfCustomer) throws IOException {

		for (int j = 0; j < namesTrack.size(); j++) {
			if (namesTrack.get(j).equalsIgnoreCase(nameOfCustomer) && userWantSerialize.equalsIgnoreCase("no")) {
				loadData();

				userNameGet();
			}

		}
	}

	public void userInput1() throws NumberFormatException, IOException {

		productCost.add(5);
		productCost.add(6);
		productCost.add(4);
		productCost.add(4);

		for (int i = 0; i < products.size(); i++) {

			System.out.println(products.get(i));
			try {
				noOfItems = Integer.parseInt(br.readLine());
				if(noOfItems<0) {
					System.out.println("Please Enter Positive Number");
					noOfItems = Integer.parseInt(br.readLine());
				}
			} catch (Exception e) {
				System.out.println("Please enter Positive number");
				int noOfItems = Integer.parseInt(br.readLine());
			}
			billOfCurrentItem = (noOfItems * (productCost.get(i)));
			totalCost.add(billOfCurrentItem);

		}
	}

	public int finalBill() {
		totalBill = 0;

		for (Integer num : totalCost) {
			totalBill += num;
		}
		return totalBill;
	}

	public void printBill() throws IOException {
		finalBill();
		System.out.println(totalBill);

	}

	public void saveData() throws IOException {
		System.out.println("Please Enter Yes if U have Money,Else No");
		userWantSerialize = br.readLine();
		if (userWantSerialize.equalsIgnoreCase("yes")) {
			System.out.println("Thanku u,We Will Print Your Bill");
			System.out.println("Dear Coustmer,Your Bill is" + totalBill);
			totalBill = 0;
			totalCost.clear();

		} else if (userWantSerialize.equalsIgnoreCase("no")) {
			ArrayList<Integer> serielArr = new ArrayList<Integer>();

			serielArr.addAll(totalCost);
			serielArr.add(totalBill);
			try {
				FileOutputStream fileOut = new FileOutputStream("C:\\Ram\\Ram.ser");
				ObjectOutputStream fOut = new ObjectOutputStream(fileOut);
				fOut.writeObject(serielArr);
				fOut.close();
				fileOut.close();
				System.out.println("Data is Saved");
				System.out.println("Your Total Bill" + totalBill);
				totalBill = 0;
				totalCost.clear();
			} catch (IOException e) {
				System.out.println("There is Something Went Wrong,Your Object NOt Serialized");
			}
		} else {
			System.out.println("You Should Enter Something");
		}

	}

	public void loadData() {
		ArrayList<Integer> deserialize = new ArrayList<>();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Ram\\Ram.ser"));
			
			@SuppressWarnings("unchecked")
			ArrayList<Integer> readObject = (ArrayList<Integer>) in.readObject();
			deserialize = readObject;
			System.out.println(deserialize);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

	}

}