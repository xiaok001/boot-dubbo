package ck.janko.controller;


import ck.janko.controller.carModel.GPSFenceList;
import ck.janko.controller.carModel.Node;
import ck.janko.controller.carModel.TotalMessage;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Controller;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    /**
     * @param args
     */
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        List<GPSFenceList> list = new ArrayList<GPSFenceList>();
        for (int i = 1; i < 10; i++) {
            GPSFenceList gps = new GPSFenceList();
            gps.setFence_Type(0);
            if (i / 2 == 1)
                gps.setFence_Type(1);
            gps.setLat_w84(String.valueOf(121.438278 + i*0.1));
            gps.setLng_w84(String.valueOf(37.440781 + i*0.1));
            gps.setRadius(100);
            list.add(gps);
        }
        Node node = new Node();
        node.setVin("vin123456");
        node.setGpsFenceList(list);
        node.setTotalMessage(new TotalMessage("S116.426567","W39.957949"));

        JSONObject jsonObj=new JSONObject(node);
        System.out.println(jsonObj.toString());
//        JSONObject jsonObj=new JSONObject();
//        jsonObj.put("Name","liangyongs");
//        jsonObj.put("Id", 31);
//        String[] likes={"java","golang","clang"};
//        jsonObj.put("Lks", likes);
//        System.out.println("发送给golang的消息是:");
//        System.out.println(jsonObj);
        try{
            socket=new Socket("127.0.0.1",50000);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            out.println(jsonObj);
            String line=in.readLine();
            System.out.println("Object read from golang side:");
            jsonObj= JSONUtil.parseObj(line);
            System.out.println(jsonObj);
            socket.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }



}
