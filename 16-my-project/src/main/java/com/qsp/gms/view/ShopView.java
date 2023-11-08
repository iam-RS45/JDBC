package com.qsp.gms.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.qsp.gms.controller.ShopController;
import com.qsp.gms.model.Product;

public class ShopView {

	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) {
		do {
			System.out.println("Select Operation to Perform : ");
			System.out.println("1.Add Product\n2.Remove Product\n3.Update Product Details\n4.Fetch Product\n0.Exit");
			System.out.print("Enter Digit Respective to Desired Option : ");

			int userInput = myInput.nextInt();
			myInput.nextLine();

			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("---- EXITED ----");
				System.exit(0);
				break;
			case 1:
				System.out.println("1.Single Product\n2.Multiple Product");
				int myProduct = myInput.nextInt();
				myInput.nextLine();

				if (myProduct == 1) {
					System.out.println("Enter Product id : ");
					int i_p_id = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Eneter Product Name : ");
					String i_p_name = myInput.nextLine();
					System.out.print("Enter Price : ");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter Product Quantity : ");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}
					if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability)) != 0) {
						System.out.println("Product Added");
					} else {
						System.out.println("Product Not Added");
					}
				} else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.print("Enter Product id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter Product Name : ");
						product.setP_name(myInput.nextLine());
						System.out.print("Enter Product Price : ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter Product Quantity : ");
						int quantity = myInput.nextInt();
						product.setP_quantity(quantity);
						myInput.nextLine();

						boolean i_p_availability = false;
						if (quantity > 0) {
							i_p_availability = true;
						}
						product.setP_availabilityl(i_p_availability);
						products.add(product);
						System.out.println("Press 1 for continue adding product, Press 0 for stop adding product");
						int toAdd = myInput.nextInt();
						if (toAdd == 0) {
							toContinue = false;
						}
					} while (toContinue);
					shopController.addMultipleProduct(products);
				}

				break;
			case 2:
				// Delete Product
				System.out.println("Enter Product id to Remove : ");
				int productIdToDelete = myInput.nextInt();
				myInput.nextLine();
				if (shopController.removeProduct(productIdToDelete) != 0) {
					System.out.println("Product Deleted");
				} else {
					System.out.println("Product with given id does not exists, No remove operation perform");

				}
				System.out.println();
				break;
			case 3:
				// Update Product

				System.out.println("Enter Product Id to Update : ");
				int productIdToUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet updateProduct = shopController.fetchProduct(productIdToUpdate);
				try {
					if (updateProduct.next()) {
						System.out.println("What you want to update ?");
						System.out.println("1.Name\n2.Price\n3.Quantity");
						System.out.println("Enter Number respective to desired option : ");
						byte updateOption = myInput.nextByte();
						myInput.nextLine();
						switch (updateOption) {
						case 1:
							System.out.println("Enter Name to Update : ");
							String nameToUpdate = myInput.nextLine();
							
							if (shopController.updateProductName(productIdToUpdate, nameToUpdate) !=0) {
								System.out.println("Data Updated");
							} else {
								System.out.println("Data Not Updated");
							}
							break;
						case 2:
							System.out.println();
							break;
						case 3:

							break;
						default:
							System.out.println("---- INVALID SELECTION ----");
							break;
						}
					} else {
						System.out.println("Product with given id does not exist, Update operation can not be perform");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				break;
			case 4:
				// Fetch product

				System.out.print("Enter Product id to fetch : ");
				int productIdToFind = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct = shopController.fetchProduct(productIdToFind);
				try {
					boolean next = fetchProduct.next();
					if (next) {
						System.out.println("Product Details");
						System.out.println("Id : " + fetchProduct.getInt(1));
						System.out.println("Name : " + fetchProduct.getString(2));
						System.out.println("Price : " + fetchProduct.getInt(3));
						System.out.println("Quantity : " + fetchProduct.getInt(4));

						if (fetchProduct.getBoolean(5)) {
							System.out.println("Availability : Available");
						} else {
							System.out.println("Availability : Not Available");
						}
					} else {
						System.out.println("Product with id : " + productIdToFind + " does not exits.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			default:
				System.out.println("---- INVALID SELECTION ----");
				break;
			}
		} while (true);
	}
}
