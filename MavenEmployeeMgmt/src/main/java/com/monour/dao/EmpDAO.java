package com.monour.dao;


import com.monour.jdbc.DBConnection;
import com.monour.model.Employee;

import java.sql.*;
import java.util.*;

public class EmpDAO {

    Scanner s = new Scanner(System.in);
    Scanner s1 = new Scanner(System.in); // to read string data

    List<Employee> list = new ArrayList<Employee>();
    Employee emp = new Employee();
    public void getEmployee(){

        Connection conn = null;
        PreparedStatement pStm = null;
        ResultSet rSet = null;

        try {
            String query = "select * from employee order by id asc";
            // get conn
            conn = DBConnection.getConnection();
            // create sql preparedStatement
            pStm = conn.prepareStatement(query);
            // execute query
            rSet = pStm.executeQuery();
            // process result set
            while (rSet.next())
            {
                //Employee emp = new Employee();

                emp.setId(rSet.getInt(1));
                emp.setName(rSet.getString(2));
                emp.setAge(rSet.getString(3));
                emp.setJob(rSet.getString(4));
                emp.setSalary(rSet.getDouble(5));

                System.out.println(emp);
            }

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage());
            //e.printStackTrace();
        } finally {
            close(conn , pStm, rSet);
        }
    }


    public int addEmployee(){
        //Employee emp = new Employee();
        int rows = 0;
        Connection conn = null;
        PreparedStatement pStm = null;
        //ResultSet rSet = null;

        System.out.println("Enter Emp Id : ");
        int empId = s.nextInt();

        System.out.println("Enter Emp Name : ");
        String empName = s1.nextLine();

        System.out.println("Enter Emp Age : ");
        String empAge = s1.nextLine();

        System.out.println("Enter Emp Job : ");
        String empJob = s1.nextLine();

        System.out.println("Enter Emp Salary : ");
        double empSalary = s.nextDouble();
        try{
            String query = "INSERT INTO `mdp`.`employee` (`id`, `name`, `aga`, `job`, `salary`) VALUES (?, ?, ?, ?, ?)";
            conn = DBConnection.getConnection();
            pStm = conn.prepareStatement(query);

            pStm.setInt(1 , empId);
            pStm.setString(2, empName);
            pStm.setString(3, empAge);
            pStm.setString(4, empJob);
            pStm.setDouble(5, empSalary);
            //rSet = pStm.executeQuery();
            rows = pStm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException : " + ex.getMessage());
        } finally {
            close(conn , pStm);
        }
        return rows;
    }

    public void updateEmployee(int empId) {

        Connection conn = null;
        PreparedStatement pStm = null;

        System.out.println("Enter New Employee Name : ");
        String empName = s1.nextLine();

        System.out.println("Enter New Employee Age : ");
        String empAge = s1.nextLine();

        System.out.println("Enter New Employee Job : ");
        String empJob = s1.nextLine();

        System.out.println("Enter New Employee Salary : ");
        double empSalary = s.nextDouble();

          try {
              String query = "UPDATE `mdp`.`employee` SET `name` = ? , `aga` = ? , `job` = ? , `salary` = ?  where `id` = ?";
              conn = DBConnection.getConnection();
              pStm = conn.prepareStatement(query);

              pStm.setString(1, empName);
              pStm.setString(2, empAge);
              pStm.setString(3, empJob);
              pStm.setDouble(4, empSalary);
              pStm.setInt(5, empId);

               int rows = pStm.executeUpdate();
               if (rows > 0) {
                   System.out.println("** Updated successfully **");
               } else {
                   System.out.println("** Failed to update **");
               }
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    public void deleteEmployee(int id){
        Connection conn = null;
        PreparedStatement pStm = null;
        try {
            String query = "DELETE FROM `mdp`.`employee` where `id` = ?";
            conn = DBConnection.getConnection();
            pStm = conn.prepareStatement(query);

            pStm.setInt(1 , id);

            int rows = pStm.executeUpdate();
            if (rows > 0) {
                System.out.println("** Deleted successfully **");
            } else {
                System.out.println("** Failed to delete **");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn, PreparedStatement pStm, ResultSet rSet) {
        try {
            if (rSet != null) {
                rSet.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            System.out.println("SQLException >> Close connection : " + e.getMessage());
            //e.printStackTrace();
        }
    }

    private void close(Connection conn, PreparedStatement pStm) {
        try {
            if (pStm != null) {
                pStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            System.out.println("SQLException >> Close connection : " + e.getMessage());
            //e.printStackTrace();
        }
    }

}

