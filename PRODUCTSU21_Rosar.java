//John Rosar
//COSC1437
//Project
//PRODUCTSU21_ROSAR.java
//Data class

public class PRODUCTSU21_Rosar {

	final double A_unitPrice = 219.99;
	final double B_unitPrice = 350.99;
	int aUnits;
	int bUnits;
	public double total;
	static int transactionNumber = 0;
	public PRODUCTSU21_Rosar(int totalSU21ProductA1, int totalSU21ProductB1, int totalSU21ProductA2, int totalSU21ProductB2, int Diff1, int Diff2, double Stats1, double Stats2) {
	}

	PRODUCTSU21_Rosar(int aUnits, int bUnits) {
		this.aUnits = aUnits;
		this.bUnits = bUnits;
	}

	void printReport(int aUnits, int bUnits, double taxRate) {

		this.transactionNumber++;

		SaleProductSU21Service_Rosar driver = new SaleProductSU21Service_Rosar();

		float tot_Product_A = (float) (aUnits * A_unitPrice);
		float tot_Product_B = (float) (bUnits * B_unitPrice);

		float subTotal = tot_Product_A + tot_Product_B;
		float tax = (float) (subTotal * taxRate) / 100;
		total = subTotal + tax;

		System.out.println("----------------------------------------");
		System.out.println("File SaleProductSU21Service_Rosar.java");
		System.out.println("Sale SU21Product Receipt – John Rosar");
		String transactionFormat = String.format("%04d", transactionNumber);
		System.out.printf("%s %20s\n", "Day: " + driver.Date, transactionFormat);

		System.out.println("----------------------------------------");
		System.out.printf("%s %8s %5s %10s\n", "SU21ProductA", "" + A_unitPrice, "" + aUnits, "" + tot_Product_A);
		System.out.printf("%s %8s %5s %10s\n", "SU21ProductB", "" + B_unitPrice, "" + bUnits, "" + tot_Product_B);
		System.out.println("----------------------------------------");
		System.out.printf("%s%29.2f\n", "Subtotal:", subTotal);
		System.out.printf("%s%34.2f\n", "TAX:", tax);
		System.out.printf("%s%32.2f\n", "Total:", total);
		System.out.println("");
		return;
	}
	void printReport2(int totalSU21ProductA1, int totalSU21ProductB1, int totalSU21ProductA2, int totalSU21ProductB2, int Diff1, int Diff2, double Stats1, double Stats2) {

		this.transactionNumber++;

		SaleProductSU21Service_Rosar driver = new SaleProductSU21Service_Rosar();


		System.out.println("------------------------------------------------------");
		System.out.println("File SaleProductSU21Service_Rosar.java");
		System.out.println("SU21Product Compare Months Report – John Rosar");
		System.out.println("Month:         Month1   Month2  Difference   %Status" );
		System.out.println("------------------------------------------------------");
		System.out.printf("%s %10s\n", "SU21ProductA", "    " + totalSU21ProductA1 + "    " + totalSU21ProductA2 + "      " + Diff1 + "        " + Stats1+"%");
		System.out.printf("%s %10s\n", "SU21ProductB", "    " + totalSU21ProductB1 + "    " + totalSU21ProductB2 + "      " + Diff2 + "        " + Stats2+"%");
		System.out.println("------------------------------------------------------");
		System.out.println("");
		return;
	}

	String printToFile() {
		return ("" + aUnits + " " + bUnits);
	}
}