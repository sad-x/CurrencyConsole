import okhttp3.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.StringReader;

public class CurrencyOnDateService {

    /**
     * Метод для возврата курсов валют по вводу пользователя
     * @see #loadXMLFromString(String)
     * @see #findCursByCode(Document, String[])
     * @param userInput - данные пользователя
     * @return double[] - массив из двух элементов с курсами заданных валют
     */
    public static double[] getCurrency(UserInput userInput) {
        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <GetCursOnDateXML xmlns=\"http://web.cbr.ru/\">\n" +
                "      <On_date>"+userInput.getDate().toString()+"</On_date>\n" +
                "    </GetCursOnDateXML>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        String url = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
        String response = okhttp(request, url);
        try{

            Document doc = loadXMLFromString(response);
            String[] codes = {userInput.getCode1(), userInput.getCode2()};
            System.out.println(findCursByCode(doc, codes)[0]);
            return findCursByCode(doc, codes);


        } catch (Exception e) {
            System.out.println(e);
        }
        return new double[] {-1, -1};
    }

    /**
     *
     * @param doc - xml документ
     * @param codes - массив из двух элементов, где элементы - коды валют
     * @return массив из двух курсов валют
     */
    public static double[] findCursByCode(Document doc, String[] codes) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        double[] res = new double[codes.length];
        for(int j = 0; j < codes.length; j++) {
            String code = codes[j];
            try{
                String s = "//ValuteData/ValuteCursOnDate[VchCode="+"\""+code+"\""+"]/Vcurs/text()";
                XPathExpression expr = xpath.compile(s);
                Object result = expr.evaluate(doc, XPathConstants.NODESET);
                NodeList nodes = (NodeList) result;
                for (int i = 0; i < nodes.getLength(); i++) {
                    res[j] = Double.parseDouble(nodes.item(i).getNodeValue());
                }
            } catch (XPathExpressionException e) {
                System.out.println(e);
            }
        }

        return res;
    }

    /**
     * Метод делает xml документ из строкового представления xml
     * @param xml - xml документ в строковом представлении
     * @return Document xml документ
     * @throws Exception
     */
    private static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    /**
     * Метод для соединения с веб-сервисом
     * @param xml - запрос к серверу
     * @param url - адрес веб-сервиса
     * @return String response - ответ сервера на POST-запрос
     */
    private static String okhttp(String xml, String url) {
        OkHttpClient client = new OkHttpClient();
        MediaType XML = MediaType.get("application/soap+xml; charset=utf-8");
        RequestBody requestBody = RequestBody.create(XML, xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
