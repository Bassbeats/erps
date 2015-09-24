package org.cuatrovientos.jomguork02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		throws ClassNotFoundException, SQLException {
		Customer myCustomer = new Customer();
	   
			Connection connection =	DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement statement = connection.createStatement();
			String sql = "create table clients (id integer, name varchar(30))";
			PreparedStatement crearCliente =					
					connection.prepareStatement("insert into clients values (?,?)");
			//creamos un prepared statement para eliminar clientes más fácilmente via nombre
			PreparedStatement eliminarPorNombre =
					connection.prepareStatement("delete from clients where name =?");
			//creamos un prepared statement para eliminar clientes más fácilmente via id
			PreparedStatement eliminarPorId =
					connection.prepareStatement("delete from clients where id =?");
					
		Scanner	reader = new Scanner(System.in);
		String option =	"";
		do	{	
			menu();
			option = reader.nextLine();	
		
		switch (option) {
		case "1": 
			String select = "select * from clients order by name desc";
    		ResultSet resultSet = statement.executeQuery(select);
    		
    		while (resultSet.next()) {
       			System.out.print("ID: " + resultSet.getInt("id"));
    			System.out.println(" Name: " + resultSet.getString("name"));
    		}
			break;
			
		case "2":
			boolean siguiente = false;
    		do {
    			System.out.print("Introduzca nombre: ");
    			name = reader.nextLine();
    			System.out.print("Introduzca id: ");
    			
    			idCard = reader.nextLine();
    			crearCliente.setInt(1, Integer.parseInt(idCard));
    			crearCliente.setString(2, name);
    			crearCliente.addBatch();
    			System.out.print("añadir más alumnos? (s/n): ");
    			if (reader.nextLine()== "s"){
    				siguiente = true;
    			}
    			
    		}while(siguiente);
    		crearCliente.executeBatch();
		case "3":
			
			break;
		case "4":
			
	    
		default:
			System.out.println();
			System.out.println("TRY AGAIN");
			break;
			}
	
		
		} while (!option.equals("4"));
		connection.close();
		}}
	private static void menu(){
		System.out.println("------------ Database Access --------------");

		System.out.println("Select one option:\n");
		System.out.println("1. List all registries");
		System.out.println("2. Insert a new registry");
		System.out.println("3. Modify a registry");
		System.out.println("4. Delete a registry");
		System.out.println("5. Delete all registries");
		System.out.println("6. Exit");

	}
	}

