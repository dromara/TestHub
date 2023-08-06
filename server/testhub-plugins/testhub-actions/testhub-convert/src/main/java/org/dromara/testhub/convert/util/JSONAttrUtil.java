package org.dromara.testhub.convert.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONAttrUtil {
    public static List<String> getAttrs(List<String> pars, JSONObject item){
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(pars,0,item,nodes);
        List<String> paths = new ArrayList<>();
        getAttrs(nodes,paths);
        for (int i=paths.size()-1;i>-1;i--){
            String path = paths.get(i);
            if(StringUtils.countMatches(path, ".")<pars.size()-1){
                paths.remove(i);
            }
        }
        return paths;
    }
    private static void getAttrs(List<String> pars, int level, JSONObject item, List<Node>paths){
        if(level>=pars.size()){
            return;
        }
        if(level<pars.size()-1){
            String path =pars.get(level);//xxxx[1]  xxxx[*] xxxx[1,3]
            String brace = path.substring(path.lastIndexOf("[")+1,path.length()); //1   *   1,3
            String base = path.substring(0,path.lastIndexOf("["));
            if(brace.indexOf(",")>-1){
                //xxxx[1,3]
                String[] nums = brace.split(",");
                try {
                    if(CollectionUtil.isEmpty(paths)){
                        int start = Integer.parseInt(nums[0]);
                        int end = Integer.parseInt(nums[1]);
                        int length = getSize(item,base);
                        if(length<0){
                            return;
                        }
                        end = Math.min(end,length);
                        for(int index=start;index<end;index++) {
                            Node node = new Node();
                            node.setLevel(level);
                            node.setPath(base + "[" + index + "]");
                            node.setAbsolute(base + "[" + index + "]");
                            paths.add(node);
                        }
                    }else {
                        for(Node node:paths){
                            List<Node> lasts = node.lastNode();
                            for (Node last : lasts) {
                                int start = Integer.parseInt(nums[0]);
                                int end = Integer.parseInt(nums[1]);
                                String basePath = last.getAbsolute();
                                basePath = basePath+"."+base;
                                int length = getSize(item,basePath);
                                if(length<0){
                                    last.remove();
                                    continue;
                                }
                                end = Math.min(end,length);
                                for(int index=start;index<end;index++) {
                                    last.add(level,base+"["+index+"]");
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    //这种有异常就按照全部的来
                }
            }else if(!brace.trim().equals("*")){
                //xxxx[1]
                int index = 0;
                try {
                    index = Integer.parseInt(brace);
                    if(CollectionUtil.isEmpty(paths)){
                        int length = getSize(item,base);
                        if(length<0){
                            return;
                        }
                        Node node = new Node();
                        node.setLevel(level);
                        node.setPath(base+"["+index+"]");
                        node.setAbsolute(base+"["+index+"]");
                        paths.add(node);
                    }else {
                        for(Node node:paths){
                            List<Node> lasts = node.lastNode();
                            for (Node last : lasts) {
                                String basePath = last.getAbsolute();
                                basePath = basePath+"."+base;
                                int length = getSize(item,basePath);
                                if(length<0){
                                    last.remove();
                                    continue;
                                }
                                last.add(level,base+"["+index+"]");
                            }
                        }
                    }
                    level++;
                    getAttrs(pars,level,item,paths);
                    return;
                }catch (Exception e){
                    //这种有异常就按照全部的来
                }
            }
            //xxxx[*]
            if(CollectionUtil.isEmpty(paths)){
                int length = getSize(item,base);
                if(length<0){
                    return;
                }
                for(int i=0;i<length;i++){
                    Node node = new Node();
                    node.setLevel(level);
                    node.setPath(base+"["+i+"]");
                    node.setAbsolute(base+"["+i+"]");
                    paths.add(node);
                }
            }else {
                for (Node node : paths) {
                    List<Node> lasts = node.lastNode();
                    for (Node last : lasts) {
                        String basePath = last.getAbsolute();
                        basePath = basePath+"."+base;
                        int length = getSize(item,basePath);
                        if(length<0){
                            last.remove();
                            continue;
                        }
                        for(int i=0;i<length;i++){
                            last.add(level,base+"["+i+"]");
                        }
                    }
                }
            }
            level++;
            getAttrs(pars,level,item,paths);
        }else if(level==pars.size()-1){
            //最后一个属性项了
            if(pars.get(level).indexOf("[")>-1){
                String path =pars.get(level);//xxxx[1]  xxxx[*] xxxx[1,3]
                String brace = path.substring(path.lastIndexOf("[")+1,path.length()-1); //1   *   1,3
                String base = path.substring(0,path.lastIndexOf("["));
                if(brace.indexOf(",")>-1){
                    //xxxx[1,3]
                    String[] nums = brace.split(",");
                    try {
                        if(CollectionUtil.isEmpty(paths)){
                            int length = getSize(item,base);
                            if(length<0){
                                return;
                            }
                            int start = Integer.parseInt(nums[0]);
                            int end = Integer.parseInt(nums[1]);
                            end = Math.min(end,length);
                            for(int index=start;index<end;index++) {
                                Node node = new Node();
                                node.setLevel(level);
                                node.setPath(base + "[" + index + "]");
                                node.setAbsolute(base + "[" + index + "]");
                                paths.add(node);
                            }
                        }else {
                            for(Node node:paths){
                                List<Node> lasts = node.lastNode();
                                for (Node last : lasts) {
                                    int start = Integer.parseInt(nums[0]);
                                    int end = Integer.parseInt(nums[1]);
                                    String basePath = last.getAbsolute();
                                    basePath = basePath+"."+base;
                                    int length = getSize(item,basePath);
                                    if(length<0){
                                        last.remove();
                                        continue;
                                    }
                                    end = Math.min(end,length);
                                    for(int index=start;index<end;index++) {
                                        last.add(level,base+"["+index+"]");
                                    }
                                }
                            }
                        }
                    }catch (Exception e){
                        //这种有异常就按照全部的来
                        if(CollectionUtil.isEmpty(paths)){
                            int length = getSize(item,base);
                            if(length<0){
                                return;
                            }
                            for(int i=0;i<length;i++){
                                Node node = new Node();
                                node.setLevel(level);
                                node.setPath(base+"["+i+"]");
                                node.setAbsolute(base+"["+i+"]");
                                paths.add(node);
                            }
                        }else {
                            for (Node node : paths) {
                                String basePath = node.getAbsolute();
                                basePath = basePath+"."+base;
                                int length = getSize(item,basePath);
                                for(int i=0;i<length;i++){
                                    node.add(level,base+"["+i+"]");
                                }
                            }
                        }
                    }
                }else {
                    // xxx[8]  xxx[*]
                    if(CollectionUtil.isEmpty(paths)){
                        int length = getSize(item,base);
                        if(length<0){
                            return;
                        }
                        Node node = new Node();
                        node.setLevel(level);
                        node.setPath(pars.get(level));
                        node.setAbsolute(pars.get(level));
                        paths.add(node);
                    }else {
                        for(Node node:paths){
                            List<Node>lasts = node.lastNode();
                            for(Node last:lasts){
                                String basePath = last.getAbsolute();
                                basePath = basePath+"."+base;
                                int length = getSize(item,basePath);
                                if(length<0){
                                    last.remove();
                                    continue;
                                }
                                last.add(level,pars.get(level));
                            }

                        }
                    }
                }
            }else {
                // xxx
                if(CollectionUtil.isEmpty(paths)){
                    int length = getSize(item,pars.get(level));
                    if(length<0){
                        return;
                    }
                    Node node = new Node();
                    node.setLevel(level);
                    node.setPath(pars.get(level));
                    node.setAbsolute(pars.get(level));
                    paths.add(node);
                }else {
                    for(Node node:paths){
                        node.add(level,pars.get(level));
                    }
                }
            }
        }
    }
    @Data
    private  static class Node{
        private String path;
        private Node per;
        private int level;
        private String absolute;
        private List<Node> subs = new ArrayList<>();

        public Node(){

        }
        public Node(Node per){
            this.per = per;
        }
        public void add(int level,String path){
            if(this.level+1==level){
                Node node = new Node(this);
                node.setLevel(level);
                node.setPath(path);
                node.setAbsolute(this.absolute+"."+path);
                subs.add(node);
            }else {
                for (Node sub : subs) {
                    sub.add(level,path);
                }
            }
        }
        public void remove(){
            if(per!=null){
                if(per.getSubs().size()>1){
                    per.subs.remove(this);
                }else {
                    per.remove();
                }
            }
        }
        public List<Node> lastNode(){
            List<Node> nodes = new ArrayList<>();
            if(CollectionUtil.isEmpty(this.getSubs())){
                nodes.add(this);
            }else {
                for (Node sub : subs) {
                    nodes.addAll(sub.lastNode());
                }
            }
            return nodes;
        }
    }
    private static int getSize(JSONObject item,String path){
        if(!JSONPath.contains(item,path)){
            return -1;
        }
        Object baseVal = JSONPath.eval(item,path,false);
        if(baseVal instanceof List){
            //列表
            return  ((List)baseVal).size();
        }else {
            //单一对象
            return -1;
        }
    }
    private static void getAttrs(List<Node> nodes,List<String> paths){
        for(Node node:nodes){
            if(CollectionUtil.isEmpty(node.getSubs())){
                paths.add(node.absolute);
            }else {
                getAttrs(node.getSubs(),paths);
            }
        }
    }
    public static void main(String[] args) {
        test19();
        test18();
        test17();
        test16();
        test15();
        test14();
        test13();
        test12();
        test11();
        test10();
        test9();
        test8();
        test7();
        test6();
        test5();
        test4();
        test3();
        test2();
        test1();
    }
    private static void test19(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[1].tags[0,1].shows[*]";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);
        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[*]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test18(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[0,1].shows[*]";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);
        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[*]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test17(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].shows[1,3]";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[1]");
        tPaths.add("users[1].tags[0].shows[2]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
           // System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test16(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].shows[1]";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[1]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test15(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].shows[*]";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[*]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
           // System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test14(){
        // 三层
        String data ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].shows[*].id";
        String[] pars = code.split("].");
        List<Node>nodes = new ArrayList<>();
        JSONAttrUtil.getAttrs(Arrays.asList(pars),0,item,nodes);

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].shows[0].id");
        tPaths.add("users[1].tags[0].shows[1].id");
        tPaths.add("users[1].tags[0].shows[2].id");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
           // System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test13(){
        // 没有的会删除全路径
        String data = "{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\"},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\",\"shows\":[{\"id\":1,\"name\":\"vinc\"},{\"id\":2,\"name\":\"vinc\"},{\"id\":3,\"name\":\"vinc\"}]},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].id";
        String[] pars = code.split("].");
        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[0].id");
        tPaths.add("users[1].tags[1].id");
        List<String> paths = getAttrs(Arrays.asList(pars),item);

        for(String path:paths){
           // System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test12(){
        //范围 超出了还
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"},{\"id\":125,\"name\":\"vinc\"},{\"id\":126,\"name\":\"vinc\"},{\"id\":127,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[1,3].tags[*]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[*]");
        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test11(){
        //范围 超出了还
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"},{\"id\":125,\"name\":\"vinc\"},{\"id\":126,\"name\":\"vinc\"},{\"id\":127,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[1,7]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[1]");
        tPaths.add("users[1].tags[1]");
        tPaths.add("users[1].tags[2]");
        tPaths.add("users[1].tags[3]");
        tPaths.add("users[1].tags[4]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test10(){
        //范围
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"},{\"id\":125,\"name\":\"vinc\"},{\"id\":126,\"name\":\"vinc\"},{\"id\":127,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[1,3]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[1]");
        tPaths.add("users[1].tags[1]");
        tPaths.add("users[1].tags[2]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
//            System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test9(){
        //不存在的属性
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tagsa[1]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test8(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[1]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[1]");
        tPaths.add("users[1].tags[1]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test7(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[*]");
        tPaths.add("users[1].tags[*]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test6(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[1].tags[*]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1].tags[*]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test5(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[1]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[1]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test4(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*]";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[*]");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    private static void test3(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[1].id";
        String[] pars = code.split("].");
        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[1].id");
        tPaths.add("users[1].tags[1].id");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }
    public static List<String> test2(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].tags[*].id";
        String[] pars = code.split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].tags[0].id");
        tPaths.add("users[0].tags[1].id");
        tPaths.add("users[1].tags[0].id");
        tPaths.add("users[1].tags[1].id");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
        return paths;
    }
    private static void test1(){
        String data  ="{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"stkInfo\":{\"code\":\"0001\",\"name\":\"vinc\"},\"users\":[{\"code\":\"0001\",\"name\":\"vinc\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]},{\"code\":\"0002\",\"name\":\"崔胜利\",\"tags\":[{\"id\":123,\"name\":\"vinc\"},{\"id\":124,\"name\":\"vinc\"}]}]}";
        JSONObject item = JSONObject.parseObject(data);
        String code = "users[*].code";
        String[] pars = "users[*].code".split("].");

        List<String> tPaths = new ArrayList<>();
        tPaths.add("users[0].code");
        tPaths.add("users[1].code");

        List<String> paths = getAttrs(Arrays.asList(pars),item);
        for(String path:paths){
            //System.out.println(path);
        }
        System.out.println("=============="+(tPaths.equals(paths)?"通过":"失败")+":"+code);
    }


}
