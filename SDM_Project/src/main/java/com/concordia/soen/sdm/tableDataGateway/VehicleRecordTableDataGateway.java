	package com.concordia.soen.sdm.tableDataGateway;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import com.concordia.soen.sdm.pojo.CatalogDetails;

	public class VehicleRecordTableDataGateway {

		public void insert(CatalogDetails newVehicle) throws SQLException, ClassNotFoundException {
			JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
			Connection connection=jdbc.getConnection(); 
			Statement statement = connection.createStatement();
			
			statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");
			statement.addBatch("INSERT INTO VehicleRentingSystem.VehicleDetails(type,make,model,year,color,licensePlate,availability,cost,version) VALUES ('"
					  + newVehicle.getType()+"','" + newVehicle.getMake()+"','" + newVehicle.getModel()+"','" + newVehicle.getYear()+"','"
					 +newVehicle.getColor()+"','" +newVehicle.getLicensePlate()+"','"+newVehicle.getAvailability()+"','"
					  +newVehicle.getCost()+"','" + newVehicle.getVersion()+"')");
			statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
			statement.executeBatch();
			System.out.println("insert   Vehicles:Stop");
			
			
		}

			public ResultSet selectMultipleRows() throws ClassNotFoundException, SQLException {
				JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
				Connection connection=jdbc.getConnection(); 
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM VehicleDetails";
			   
				ResultSet rs = statement.executeQuery(sql);
				 System.out.println("Select multiple vehicles:Finish"); 
				return rs; 
		}

			public ResultSet selectVehicle(String licensePlate) throws SQLException, ClassNotFoundException {
				JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
				Connection connection=jdbc.getConnection(); 
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM VehicleDetails where licensePlate='"+licensePlate+"'";
			  
				ResultSet rs = statement.executeQuery(sql);
			    System.out.println("Select vehicles:Finish"); 
			    
			    
				return rs; 
			}
			
			public boolean updateVehicle(CatalogDetails vehicle, int version) throws ClassNotFoundException, SQLException {
				JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
				Connection connection=jdbc.getConnection(); 
				Statement statement = connection.createStatement();
				String updateQuery = "UPDATE VehicleDetails SET type='"+ vehicle.getType()+"',"+
						   "make='"+ vehicle.getMake()+"',"+
							"model='"+vehicle.getModel()+"',"+
							"year='"+vehicle.getYear()+"',"+
							"color='"+vehicle.getColor()+"',"+
							"availability='"+vehicle.getAvailability()+"',"+
							"version='"+vehicle.getVersion()+"',"+
							"cost='"+ vehicle.getCost()+"'"+
							" WHERE licensePlate='"+vehicle.getLicensePlate()+"' and version='"+version+"'";
				
				statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
				statement.addBatch(updateQuery);
				statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
				System.out.println("update Statement:Start");
			int[] result=statement.executeBatch();
			
			 connection.close();  
				
			 if(result[1]<=0) {
				 return false;
			 }else {
				 return true;
				 
			 }
			}


			public void delete(String licensePlate) throws ClassNotFoundException, SQLException {
				// TODO Auto-generated method stub
				JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
				Connection connection=jdbc.getConnection(); 
				Statement statement = connection.createStatement();
				String deleteTransactionQuery= "DELETE FROM VehicleRentingSystem.rentedVehiclesRecord  WHERE licensePlate='"+licensePlate+"'";
				String deleteQuery =  "DELETE FROM VehicleDetails  WHERE licensePlate='"+licensePlate+"'";
				statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");   
				statement.addBatch(deleteQuery);
				statement.addBatch(deleteTransactionQuery);
				statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
				int[] result=statement.executeBatch();
				  
			}
			
			public ResultSet getVehicleRentRecordsForVehicle(String licensePlate) throws ClassNotFoundException, SQLException {
				JdbcConnectionManager jdbc=JdbcConnectionManager.getjdbc();
				Connection connection=jdbc.getConnection(); 
				Statement statement = connection.createStatement();
			
				String  selectQuery="select * from VehicleRentingSystem.rentedVehiclesRecord where licensePlate='"+licensePlate+"' and (status='rented' or status='reserved')";
			ResultSet rs=statement.executeQuery(selectQuery);
			return rs;
			}
			

	}



