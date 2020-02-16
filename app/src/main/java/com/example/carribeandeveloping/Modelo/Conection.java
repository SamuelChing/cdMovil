package com.example.carribeandeveloping.Modelo;
//Libs that are needed
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conection extends AsyncTask <String,Void,Integer>{
    static Connection conexionMySQL;
    static String nombreLogin;
    static String contra;
    int tipo;
   // final String url = "jdbc:mysql:/192.168.1.2:3306/bringme";
    final static String url="jdbc:mysql://servidor-caribbean.mysql.database.azure.com:3306/db_delmonte?useSSL=true&requireSSL=false";
     final static String cuentaSql = "gerente@servidor-caribbean";
     final static String contraSql = "Nahum123";
    public Integer  doInBackground(String... strings)
    {
        try
        {//String url ="jdbc:mysql://servidor-caribbean.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false"; //myDbConn = DriverManager.getConnection(url, "gerente@servidor-caribbean", {your_password});
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexionMySQL = DriverManager.getConnection(this.url, this.cuentaSql,this.contraSql);
            Statement estado = conexionMySQL.createStatement();
            ResultSet result;
            //String peticion ="CALL Login('"+nombreLogin+"','"+contra+"');";
            String peticion="SELECT * from usuario where Nombre ='Nahum';";
            result = estado.executeQuery(peticion);
            if(result.next())
                tipo =0;
            else{
                tipo=-1;
            }

            Log.i("Conexion","Si se pudo");
            return tipo;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public static  void main(String[] args) {
        // this code will be run
        int tipo= getLogin("Nash33","Nahum123");
        System.out.println(tipo);
    }
    /**
     * Esta función retorna un valor dependiendo del usuario consultado
     * @param user nombre de usuario
     * @param pass contraseña del usuario
     * @return
     */
    public static  int getLogin(String user,String pass)
    {
        nombreLogin = user;
        contra = pass;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexionMySQL = DriverManager.getConnection(url, cuentaSql, contraSql);
            Statement estado = conexionMySQL.createStatement();
            ResultSet result;
            String peticion = "CALL Login('Nash33','Nahum123');";
            result = estado.executeQuery(peticion);
            result.next();
            int res = result.getInt("tipo");
            String user_id = result.getString("Cedula");
            System.out.println("La cedula es del usuarios es: "+user_id);
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param command
     * @param number
     * @param model
     * @param serie
     * @param fechaProduccion
     * @param fechaServicio
     * @param garantía
     * @param costumer
     * @param ID
     * @return número que identifica que 0 fue existoso y otro resultado fue que algo salió mal
     */
    public static int addOrUpdateContainer(int command,String number,String model, String serie, String fechaProduccion,
                                   String fechaServicio, String garantía, String costumer, String ID){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexionMySQL = DriverManager.getConnection(url, cuentaSql, contraSql);
            Statement estado = conexionMySQL.createStatement();
            ResultSet result;
            String peticion = "CALL GestionContenedor("+command+",'"+number+"','"+model+"',"+"'"+serie+"',"+"'"+fechaProduccion+","+"'"+fechaServicio+"',"+"'"+garantía+"',"+"'"+costumer+"','"+ID+"');";
            result = estado.executeQuery(peticion);
            result.next();
            int res = result.getInt("Result");
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return -1;

    }

    /**
     *
     * @returns all containers from the database
     */
    public static ResultSet getContainer(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexionMySQL = DriverManager.getConnection(url, cuentaSql, contraSql);
            Statement estado = conexionMySQL.createStatement();
            ResultSet result;
            String peticion = "SELECT `contenedor`.`Numero`," +
                    "    `contenedor`.`Modelo`," +
                    "    `contenedor`.`Serie`," +
                    "    `contenedor`.`Fecha_Produccion`," +
                    "    `contenedor`.`Fecha_Servicio`," +
                    "    `contenedor`.`Garantia`," +
                    "    `contenedor`.`Customer`," +
                    "    `contenedor`.`Estado`" +
                    "FROM `db_delmonte`.`contenedor`;";
            result = estado.executeQuery(peticion);
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This function updates the container state
     * @param id
     * @returns 0 if container is activated/Inactivated correctly and -1 if not.
     */
    public static int inActivateContainer(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexionMySQL = DriverManager.getConnection(url, cuentaSql, contraSql);
            Statement estado = conexionMySQL.createStatement();
            ResultSet result;
            String peticion = "CALL GestionContenedor('1','','','','','','','','"+id+"');";
            result = estado.executeQuery(peticion);
            result.next();
            int res = result.getInt("Result");
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

}
