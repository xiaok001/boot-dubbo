package ck.janko.controller;


import ck.janko.controller.carModel.ComFenceXY;
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

        //圆形
        GPSFenceList gpsFenceList=new GPSFenceList();
        gpsFenceList.setFence_Type(0);//圆形
        gpsFenceList.setRadius(1000);

        List<ComFenceXY> comFenceXYList=new ArrayList<ComFenceXY>();
        ComFenceXY comFenceXY=new ComFenceXY();
        comFenceXY.setLat_w84("116.464224");
        comFenceXY.setLng_w84("39.92343");
        comFenceXYList.add(comFenceXY);
        gpsFenceList.setComFenceXYList(comFenceXYList);

        list.add(gpsFenceList);


        //添加多边形
        gpsFenceList=new GPSFenceList();
        gpsFenceList.setFence_Type(1);//多边形
        String[] array={"116.379136,39.955073","116.361889,39.947551","116.361889,39.947551","116.40472,39.905942",
                "116.430016,39.910369","116.442377,39.932504","116.438928,39.954188","116.409319,39.955737"};

        for (int i =0; i<array.length;i++){
            comFenceXY=new ComFenceXY();
            comFenceXY.setLat_w84(array[i].split(",")[0]);
            comFenceXY.setLng_w84(array[i].split(",")[1]);
            comFenceXYList.add(comFenceXY);
        }
        gpsFenceList.setComFenceXYList(comFenceXYList);
        list.add(gpsFenceList);


        Node node = new Node();
        node.setVin("vin123456");
        node.setGpsFenceList(list);//围栏的坐标点
//        node.setTotalMessage(new TotalMessage("S116.426567","W39.957949")); //GPS的坐标点
        node.setTotalMessage(new TotalMessage("S116.397534","W39.928963"));
        JSONObject jsonObj=new JSONObject(node);
        System.out.println(jsonObj.toString());
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
