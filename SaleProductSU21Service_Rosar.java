//John Rosar
//COSC1437
//Project
//SaleProductSU21Service_ROSAR.java
//Driver class

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SaleProductSU21Service_Rosar{
	
	final static double TAX_RATE = 8.25;

	static String FileName;
	static String data;
	static float paidAmount;

	static Scanner sc = new Scanner(System.in);
	static Date date_acc = new Date();
	@SuppressWarnings("deprecation")
	static int day = date_acc.getDate();
	@SuppressWarnings("deprecation")
	static int month = date_acc.getMonth() + 1;
	@SuppressWarnings("deprecation")
	static int year = date_acc.getYear() + 1900;
	static String Date = "" + month + "/" + day + "/" + year;
	static String name;


	public static void main(String[] args) {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date = format.format(date_acc);

		int choice = menu();
		while (choice != 0) {
			if (choice == 1) {
				getMoney();
				choice = menu();
			} else if (choice == 2) {
				DAY_REPORT();
				choice = menu();
			} else if (choice == 3) {
				MONTH_REPORT();
				choice = menu();
			} else if (choice == 4) {
				Compare_Months();
				choice = menu();
			}
		}
	}

	private static void Compare_Months() {
		int totalSU21ProductA1=0;
		int totalSU21ProductB1=0;
		int totalSU21ProductA2=0;
		int totalSU21ProductB2=0;
		int Diff1;
		int Diff2;
		double Stats1;
		double Stats2;

		System.out.println("Please enter Month report to run(mm/yyyy):");
		String s1 = sc.next();
		String[] ar1 = s1.split("/");
		int m1 = Integer.parseInt(ar1[0]);
		int y1 = Integer.parseInt(ar1[1]);
		String inFile1 = readFileAsString("monthFile_" + String.format("%02d", m1) + String.format("%04d", y1) + ".txt");
		if (inFile1 == null) {
			System.out.println("empty file");
		} else {
			String[] inData1 = inFile1.split("\n");
			for (int i = 0; i < inData1.length; i++) {
				String[] DataSplit = inData1[i].split(" ");
				totalSU21ProductA1 += Double.parseDouble(DataSplit[1]);
				totalSU21ProductB1 += Double.parseDouble(DataSplit[2]);
			}
			System.out.println("Please enter Month report to run(mm/yyyy):");
			String s2 = sc.next();
			String[] ar2 = s2.split("/");
			int m2 = Integer.parseInt(ar2[0]);
			int y2 = Integer.parseInt(ar2[1]);
			String inFile2 = readFileAsString("monthFile_" + String.format("%02d", m2) + String.format("%04d", y2) + ".txt");
			if (inFile2 == null) {
				System.out.println("empty file");
			} else {
				String[] inData2 = inFile2.split("\n");
				for (int i = 0; i < inData2.length; i++) {
					String[] DataSplit = inData2[i].split(" ");
					totalSU21ProductA2 += Double.parseDouble(DataSplit[1]);
					totalSU21ProductB2 += Double.parseDouble(DataSplit[2]);
				}
			Diff1 = totalSU21ProductA2 - totalSU21ProductA1;
			Diff2 = totalSU21ProductB2 - totalSU21ProductB1;
			Stats1 = (100 * Diff1) / totalSU21ProductA1;
			Stats2 = (100 * Diff2) / totalSU21ProductB1;

			PRODUCTSU21_Rosar driver = new PRODUCTSU21_Rosar(totalSU21ProductA1, totalSU21ProductB1,totalSU21ProductA2,totalSU21ProductB2, Diff1, Diff2, Stats1, Stats2);
			driver.printReport2(totalSU21ProductA1, totalSU21ProductB1, totalSU21ProductA2, totalSU21ProductB2, Diff1, Diff2, Stats1, Stats2);
			String dataInFile1 = ar1[0] + " " + totalSU21ProductA1 + " " + totalSU21ProductB1 +"\n";
			appendToFile("yearSale_" + ar1[1] + ".txt", dataInFile1);
			
			return;
		}
		return;
	}}

	private static void MONTH_REPORT() {
		int totalSU21ProductA=0;
		int totalSU21ProductB=0;

		System.out.println("Please enter Month report to run(mm/yyyy):");
		String s = sc.next();
		String[] ar = s.split("/");
		int m = Integer.parseInt(ar[0]);
		int y = Integer.parseInt(ar[1]);
		String File_Split = readFileAsString("monthFile_" + String.format("%02d", m) + String.format("%04d", y) + ".txt");
		if (File_Split == null) {
			System.out.println("empty file");
		} else {
			String[] inData1 = File_Split.split("\n");
			for (int i = 0; i < inData1.length; i++) {
				String[] DataSplit = inData1[i].split(" ");
				totalSU21ProductA += Double.parseDouble(DataSplit[1]);
				totalSU21ProductB += Double.parseDouble(DataSplit[2]);
			}
			PRODUCTSU21_Rosar driver = new PRODUCTSU21_Rosar(totalSU21ProductA, totalSU21ProductB);
			driver.printReport(totalSU21ProductA, totalSU21ProductB, TAX_RATE);
			String dataInFile = ar[0] + " " + totalSU21ProductA + " " + totalSU21ProductB +"\n";
			appendToFile("yearSale_" + ar[1] + ".txt", dataInFile);
			return;
		}
		return;
	}

	private static void DAY_REPORT() {
		int totalSU21ProductA=0;
		int totalSU21ProductB=0;

		System.out.println("Enter date (mm/dd/yyyy):");
		String s = sc.next();
		String[] a = s.split("/");
		int m = Integer.parseInt(a[0]);
		int date_acc = Integer.parseInt(a[1]);
		int y = Integer.parseInt(a[2]);
		String File_Split = readFileAsString(
				"dayFile_" + String.format("%02d", m) + String.format("%02d", date_acc) + String.format("%04d", y) + ".txt");
		if (File_Split == null) {
			System.out.println("empty file");
		} else {
			String[] inData1 = File_Split.split("\n");
			for (int i = 0; i < inData1.length; i++) {
				//System.out.println(inData1.length);
				String[] DataSplit = inData1[i].split(" ");
				totalSU21ProductA += Double.parseDouble(DataSplit[1]);
				totalSU21ProductB += Double.parseDouble(DataSplit[2]);
			}
			PRODUCTSU21_Rosar driver = new PRODUCTSU21_Rosar(totalSU21ProductA, totalSU21ProductB);
			driver.printReport(totalSU21ProductA, totalSU21ProductB, TAX_RATE);
			String dataInFile = a[0] + " " + totalSU21ProductA + " " + totalSU21ProductB +"\n";
			appendToFile("monthFile_" + a[0] + a[2] + ".txt", dataInFile);
			return;
		}
	}

	private static int menu() {
		System.out.println("File SaleProductSU21Service_Rosar.java");
		System.out.println("SU21Product Menu – John Rosar");
		System.out.println("-----------------------------------------");
		System.out.println("1.   Sale Product");
		System.out.println("2.   Day Sale Report");
		System.out.println("3.   Month Sale Report");
		System.out.println("4.   Compare Two Months Report");
		System.out.println("0.   EXIT");
		int option = sc.nextInt();
		if (option >= 0 && option < 5) {
			return option;
		} else {
			System.out.println("enter a valid choice");
			return menu();
		}
	}

	static void getMoney() {
		int c1 = 0, c2 = 0;
		while (true) {
			System.out.println("File SaleProductSU21Service_Rosar.java");
			System.out.println("SU21Product Menu – John Rosar");
			System.out.println("Day:" + Date);
			System.out.println("----------------------------------------");
			System.out.println("1.   SU21Product A");
			System.out.println("2.   SU21Product B");
			System.out.println("0.   Exit");
			System.out.println("Type 1, 2 or 0 to continue:");
			int i = sc.nextInt();
			if (i == 1) {
				System.out.println("Enter quantity");
				c1 += sc.nextInt();
			} else if (i == 2) {
				System.out.println("Enter quantity");
				c2 += sc.nextInt();
			} else if (i == 0) {
				break;
			} else {
				System.out.println("Enter valid input");
				continue;
			}
		}

		System.out.println("Enter amount paid:");
		paidAmount = sc.nextFloat();
		PRODUCTSU21_Rosar driver = new PRODUCTSU21_Rosar(c1, c2);
		driver.printReport(c1, c2, TAX_RATE);
		System.out.printf("%s%27.2f\n", "Money Paid:", paidAmount);
		System.out.printf("%s%31.2f\n", "Change:", (paidAmount - driver.total));
		System.out.println();
		FileName = "daySale_" + String.format("%02d", month) + String.format("%02d", day)
		+ String.format("%04d", year) + ".txt";
		data = String.format("%02d", day) + String.format("%04d", driver.transactionNumber ) + " " + c1 + " " + c2 + "\n";
		appendToFile(FileName, data);
		return;
	}

	static void appendToFile(String FileName, String information) {
		File file = new File(FileName);
		try {
			FileWriter fileWRITE = new FileWriter(file, true);
			BufferedWriter buffREAD = new BufferedWriter(fileWRITE);
			buffREAD.write(information);
			buffREAD.close();
			fileWRITE.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return;
	}

	public static String readFileAsString(String fileName) {
		File found = new File(fileName);
		if (!found.exists()) {
			System.out.println("No such file found");
			return null;
		}

		String information = "";
		try {
			information = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return information;
	}
}

