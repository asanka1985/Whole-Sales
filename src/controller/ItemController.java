
package controller;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Customer;
import model.Item;

public class ItemController {
    public static boolean addItem(Item item) throws ClassNotFoundException, SQLException{
     
       String SQL="Insert into Item Values(?,?,?,?)";
       Connection connection = DBConnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement(SQL);
       pstm.setObject(1,item.getCode());
       pstm.setObject(2,item.getDescription());
       pstm.setObject(3,item.getUnitPrice());
       pstm.setObject(4,item.getQtyOnHand());
       return pstm.executeUpdate()>0;
   }
    
    public static Item searchItem(String code) throws ClassNotFoundException, SQLException{
       Connection connection=DBConnection.getInstance().getConnection();
       Statement stm=connection.createStatement();
       String SQL="Select * From Item where code='"+code+"'";
       ResultSet rst=stm.executeQuery(SQL);
          return rst.next()? new Item(code,rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("qtyOnHand")):null;
   }
      
    public static boolean updateItem(Item item) throws ClassNotFoundException, SQLException{
       String SQL="Update Item set description=?, unitPrice=?, qtyOnHand=? where code=?";
       Connection connection = DBConnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement(SQL);
       pstm.setObject(1,item.getDescription());
       pstm.setObject(2,item.getUnitPrice());
       pstm.setObject(3,item.getQtyOnHand());
       pstm.setObject(4,item.getCode());
       return pstm.executeUpdate()>0;
    }
    
    public static boolean deleteItem(String code) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Item where code='"+code+"'")>0;
    }
    
     public static Item[] getAllItems() throws ClassNotFoundException, SQLException{
        String SQL="Select * From Item";
        Connection connection= DBConnection.getInstance().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        int count=0;
        while(rst.next()){
            count++;
        }
        Item[] itemArray=new Item[count];
        rst.beforeFirst();
        for (int i = 0; i <count; i++) {
            rst.next();
            itemArray[i]=new Item(rst.getString("code"),rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("QtyOnHand"));
        }
        return itemArray;
    }
}
