package tools;

import java.util.List;
import java.util.Map;

/**
 * @author liubo
 *         定义XML文档建立与解析的接口
 */
public abstract class XmlDocument {
    /**
     * 建立XML文档
     *
     * @param fileName 文件全路径名称
     */
    public void createXml(String fileName){

    }

    /**
     * 解析XML文档
     *
     * @param fileName 文件全路径名称
     */
     public static Map<String,List<Map<String,String>>> parserXml(String fileName){
         return null;
     }
} 