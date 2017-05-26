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

public class App {
    public static  void main(String[] args) throws IOException{
        JSONParser parser=new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/home/aptus-hp/JsonRead/src/main/java/read.json"));
            JSONObject jsonObj = (JSONObject) obj;
            String name=(String)jsonObj.get("name");
            System.out.println("Name: "+name);

            JSONArray arr=(JSONArray) jsonObj.get("rounds");

            for(Object a:arr) {
                JSONObject aObj=(JSONObject)a;
                String matchname = (String) aObj.get("name");
                System.out.println("Match Name: "+matchname);

                JSONArray matcharr = (JSONArray) aObj.get("matches");
                for(Object m:matcharr){
                    JSONObject mObj=(JSONObject)m;

                    String date=(String)mObj.get("date");
                    System.out.println("Date: "+date);

                    JSONObject t1Obj=(JSONObject) mObj.get("team1");
                    JSONObject t2Obj=(JSONObject) mObj.get("team2");


                    String t1key=(String )t1Obj.get("key");
                    System.out.println("Team1 Key: "+t1key);
                    String t1name=(String )t1Obj.get("name");
                    System.out.println("Team 1 Name: "+t1name);
                    String t1code=(String )t1Obj.get("code");
                    System.out.println("Team1 Code: "+t1code);

                    Long score1=(Long)mObj.get("score1");
                    System.out.println("Score1: "+score1);


                    String t2key=(String )t2Obj.get("key");
                    System.out.println("Team2 Key: "+t2key);
                    String t2name=(String )t2Obj.get("name");
                    System.out.println("Team2 Name: "+t2name);
                    String t2code=(String )t2Obj.get("code");
                    System.out.println("Team2 Code: "+t2key);

                    Long score2=(Long) mObj.get("score2");
                    System.out.println("Score2: "+score2);




                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
