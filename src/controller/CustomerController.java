/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Customer;


public class CustomerController {
   public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
     
       String SQL="Insert into Customer Values(?,?,?,?)";
       Connection connection = DBConnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement(SQL);
       pstm.setObject(1,customer.getId());
       pstm.setObject(2,customer.getName());
       pstm.setObject(3,customer.getAddress());
       pstm.setObject(4,customer.getSalary());
       return pstm.executeUpdate()>0;
   }
   public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
       Connection connection=DBConnection.getInstance().getConnection();
       Statement stm=connection.createStatement();
       String SQL="Select * From Customer where id='"+id+"'";
       ResultSet rst=stm.executeQuery(SQL);
          return rst.next()? new Customer(id,rst.getString("name"),rst.getString("address"),rst.getDouble("salary")):null;
   }
   
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
       String SQL="Update Customer set name=?, address=?, salary=? where id=?";
       Connection connection = DBConnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement(SQL);
       pstm.setObject(1,customer.getName());
       pstm.setObject(2,customer.getAddress());
       pstm.setObject(3,customer.getSalary());
       pstm.setObject(4,customer.getId());
       return pstm.executeUpdate()>0;
    }
    
    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
    }
    
    public static Customer[] getAllCustomers() throws ClassNotFoundException, SQLException{
        String SQL="Select * From Customer";
        Connection connection= DBConnection.getInstance().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        int count=0;
        while(rst.next()){
            count++;
        }
        Customer[] customerArray=new Customer[count];
        rst.beforeFirst();
        for (int i = 0; i <count; i++) {
            rst.next();
            customerArray[i]=new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary"));
        }
        return customerArray;
    } 
}
