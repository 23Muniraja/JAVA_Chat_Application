import java.sql.*;

class MySQL1 {
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/UserLogin"; // table details
        String username = "root"; // MySQL credentials
        String password = "Muni";
        String query = "select Password,User_Id from UserLogin where User_Id=2"; // query to be run
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query); // Execute query
        rs.next();
        String name = rs.getString("User_Id"); // Retrieve name from db
        String name1 = rs.getString("Password");
        System.out.println(name+"\n"+name1); // Print result on console
        st.close(); // close statement
        con.close(); // close connection
        System.out.println("Connection Closed....");
    }
}