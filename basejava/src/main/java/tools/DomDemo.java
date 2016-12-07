package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author liubo
 *         DOM生成与解析XML文档
 */
public class DomDemo extends XmlDocument {
    private Document document;
    private String fileName;
    private static final Logger logger = LoggerFactory.getLogger(DomDemo.class);
    public DomDemo() {
        init();
    }

    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createXml(String fileName) {
        Element root = this.document.createElement("employees");
        this.document.appendChild(root);
        Element employee = this.document.createElement("employee");
        Element name = this.document.createElement("name");
        name.appendChild(this.document.createTextNode("丁宏亮"));
        employee.appendChild(name);
        Element sex = this.document.createElement("sex");
        sex.appendChild(this.document.createTextNode("m"));
        employee.appendChild(sex);
        Element age = this.document.createElement("age");
        age.appendChild(this.document.createTextNode("30"));
        employee.appendChild(age);
        root.appendChild(employee);
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("生成XML文件成功!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String,List<Map<String,String>>> parserXml(String xmlContent) {
        Map<String, List<Map<String, String>>> xmlMap = new HashMap<>(16);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmlContent);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            NodeList nodeList = document.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                List<Map<String, String>> eleList = new ArrayList<>(16);
                Map<String, String> attrMap = new HashMap<>(16);
                NodeList employeeInfo = node.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node1 = employeeInfo.item(j);
                    NamedNodeMap nodeMap = node1.getAttributes();
                    for (int k = 0; nodeMap != null && k < nodeMap.getLength(); k++) {
                        Node node2 = nodeMap.item(k);
                        attrMap.put(node2.getNodeName(), node2.getNodeValue());
                    }
                    eleList.add(attrMap);
                }
                xmlMap.put(node.getNodeName(),eleList);
            }
            logger.info("data {}",xmlMap);
            logger.info("解析完毕");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return xmlMap;
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                "<cinemas>\n" +
                "<cinema code=\"0100\" name=\"Paris van Java\" shortname=\"PVJ\" address=\"Sukajadi #137 - 139\" city=\"Bandung\" telephone=\"+622282063630\" auditypes=\"N\" image=\"https://api.cgvblitz.com/uploads/cinemas/PVJ.JPG\"/>\n" +
                "<cinema code=\"0200\" name=\"Grand Indonesia\" shortname=\"GI\" address=\"M H Thamrin #1\" city=\"Jakarta\" telephone=\"+622123580200\" auditypes=\"N;Y\" image=\"https://api.cgvblitz.com/uploads/cinemas/GI.JPG\"/>\n" +
                "<cinema code=\"0300\" name=\"Pacific Place\" shortname=\"PP\" address=\"Sudirman lot 52 - 53\" city=\"Jakarta\" telephone=\"+622151400800\" auditypes=\"N;V\" image=\"https://api.cgvblitz.com/uploads/cinemas/PP.JPG\"/>\n" +
                "<cinema code=\"0400\" name=\"Mall of Indonesia\" shortname=\"MOI\" address=\"Boulevard Barat Raya Kelapa Gading\" city=\"Jakarta\" telephone=\"+622145868100\" auditypes=\"N;V\" image=\"https://api.cgvblitz.com/uploads/cinemas/MOI.JPG\"/> <cinema code=\"0500\" name=\"Teras Kota\" shortname=\"TK\" address=\"BSD City sector IV CBD Lot VII B\" city=\"Tangerang\" telephone=\"+622129915725\" auditypes=\"N\" image=\"https://api.cgvblitz.com/uploads/cinemas/TK.JPG\"/>\n" +
                "<cinema code=\"0600\" name=\"Central Park\" shortname=\"CP\" address=\"S Parman lot 28\" city=\"Jakarta\" telephone=\"+622156985288\" auditypes=\"N;V;O\" image=\"https://api.cgvblitz.com/uploads/cinemas/CP.JPG\"/>\n" +
                "<cinema code=\"0700\" name=\"Bekasi Cyber Park\" shortname=\"BCP\" address=\"K H Noor Alie #177\" city=\"Bekasi\" telephone=\"+622188856688\" auditypes=\"N\" image=\"https://api.cgvblitz.com/uploads/cinemas/BCP.JPG\"/>\n" +
                "<cinema code=\"0800\" name=\"Plaza Balikpapan\" shortname=\"BPP\" address=\"Plaza Balikpapan, first floor, Klandasan Ilir\" city=\"Balikpapan\" telephone=\"+62542423448\" auditypes=\"N\" image=\"https://api.cgvblitz.com/uploads/cinemas/BPP.JPG\"/> </cinemas>";
        parserXml(xml);
    }
} 

