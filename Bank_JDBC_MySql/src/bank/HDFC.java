package bank;
import java.sql.*;
import java.util.Scanner;


class Operations{
	
	void insert(Statement s,String cus_name,int cus_age ) throws SQLException
	{
		String sql = "insert into customer_details (cus_name,cus_age) values('"+cus_name+"',"+cus_age+")"; 
           
           int count = s.executeUpdate(sql);
           System.out.println();
           
           if (count == 1)  
           System.out.println("Inserted customer record successfully into the database \n ");  
           else 
           System.out.println("Insertion failed.");
	}	
	
	
	
	void display(Statement s) throws SQLException
	{
		String sql = "Select * from customer_details";
		ResultSet rs=s.executeQuery(sql);
		System.out.println("                  CUSTOMER_DETAILS TABLE");
        System.out.println("*****************************************************************");
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " +rs.getInt(3));
			
			}
		System.out.println("*****************************************************************");
		
	}

}

public class HDFC {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			Statement s = con.createStatement();
			
            s.execute("create database HDFC"); 
            s.execute("use HDFC");
            System.out.println("Database creation successful \n");
            
            s.execute("create table customer_details(cus_id int auto_increment primary key,cus_name varchar(50) not null,cus_age int not null)");
            System.out.println("customer_details table created successfully in the database"); 
            System.out.println();
            
            System.out.println("Welcome to HDFC Bank");
            System.out.println("------------------------");
             
            	
            int opt;
            Operations obj=new Operations();
            do{
	            System.out.println("Select one option:\n1.Insert tuples into the table \n2.Display table \n3.Exit");
	            opt=sc.nextInt();
	            
	            switch(opt){  
	    	      
	    	    case 1:  System.out.println("Enter customer name:"); 
	    	    		 String name=sc.next();
	    	    		 System.out.println("Enter customer age:"); 
	    	    		 int age=sc.nextInt();
	    	    		 obj.insert(s,name,age );
	    	    		 break;  
	    	   
	    	    
	    	    
	    	    case 2:  obj.display(s);
	    	    	     break;  
	    	   
	    	    
	    	    case 3:  con.close();
	    	    		 s.close();
	    	    		 System.out.println("..");
	    	    		 System.out.println("...");
	    	    		 System.out.println("Connection terminated successfully....");
	    	    		 System.exit(0);
	    	    		 break;  
	    	     
	    	    default:System.out.println("Enter a valid input");
	            
	            }
            
            
            }while(true);
            
            
            
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		
	}
}
	


