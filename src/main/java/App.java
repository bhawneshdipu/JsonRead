/**
 * Created by aptus-hp on 25/5/17.
 */
import java.awt.peer.SystemTrayPeer;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.sql.*;

public class App {
    public static  void main(String[] args) throws IOException{
        JSONParser parser=new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/home/aptus-hp/JsonRead/src/main/java/read.json"));
            JSONObject jsonObj = (JSONObject) obj;
            String name=(String)jsonObj.get("name");

            JSONArray arr=(JSONArray) jsonObj.get("rounds");

            /* jdbc connection */
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String URL = "jdbc:mysql://localhost/jsonread";
                String USER = "root";
                String PASS = "";
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt=null;
                try{
                    stmt=conn.createStatement();
                    String sql="CREATE TABLE IF NOT EXISTS matchtable (name varchar(100),matchname varchar(100),date varchar(50),t1key varchar(100),t1name varchar(100),t1code varchar(100),t2key varchar(100),t2name varchar(100),t2code varchar(100),score1 varchar(50),score2 varchar(100))";
                    Boolean ret = stmt.execute(sql);
                    System.out.println(ret.toString());
                }catch (SQLException s){
                    System.out.println("error in create sql ");
                }finally {
                    stmt.close();
                }




                for(Object a:arr) {
                JSONObject aObj=(JSONObject)a;
                String matchname = (String) aObj.get("name");

                JSONArray matcharr = (JSONArray) aObj.get("matches");
                    for(Object m:matcharr){
                        System.out.print("Name: "+name);
                        System.out.print("Match Name: "+matchname);


                        JSONObject mObj=(JSONObject)m;

                        String date=(String)mObj.get("date");
                        System.out.print("Date: "+date);

                        JSONObject t1Obj=(JSONObject) mObj.get("team1");
                        JSONObject t2Obj=(JSONObject) mObj.get("team2");


                        String t1key=(String )t1Obj.get("key");
                        System.out.print("Team1 Key: "+t1key);
                        String t1name=(String )t1Obj.get("name");
                        System.out.print("Team 1 Name: "+t1name);
                        String t1code=(String )t1Obj.get("code");
                        System.out.print("Team1 Code: "+t1code);

                        Long score1=(Long)mObj.get("score1");
                        System.out.print("Score1: "+score1);


                        String t2key=(String )t2Obj.get("key");
                        System.out.print("Team2 Key: "+t2key);
                        String t2name=(String )t2Obj.get("name");
                        System.out.print("Team2 Name: "+t2name);
                        String t2code=(String )t2Obj.get("code");
                        System.out.print("Team2 Code: "+t2key);

                        Long score2=(Long) mObj.get("score2");
                        System.out.println("Score2: "+score2);


                        stmt=null;
                            try{
                                stmt=conn.createStatement();
                                String sql="INSERT INTO jsonread.matchtable (name,matchname,date,t1key,t1name,t1code,t2key,t2name,t2code,score1,score2) VALUES" +
                                    "('"+name+"','"+matchname+"','"+date+"','"+t1key+"','"+t1name+"','"+t1code+"','"+t2key+"','"+t2name+"','"+t2code+"','"+score1+"','"+score2+"')";
                                int ret = stmt.executeUpdate(sql);
                                System.out.println(ret);

                            }catch (SQLException s){
                                System.out.println("error in sql ");
                            }finally {
                                stmt.close();
                            }

                        }
                    }
                }
                catch(ClassNotFoundException ex) {
                    System.out.println("Error: unable to load driver class!");
                    System.exit(1);
                }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
