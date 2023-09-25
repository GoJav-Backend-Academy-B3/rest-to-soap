package com.phincon.wls.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.annotation.XmlNative;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

public class Bind {
    public static <T> String parseObject(T object) throws Exception {
        String user = new ObjectMapper().writeValueAsString(object);
        JsonNode jsonNode = new ObjectMapper().readTree(user);

        // Create a DOM Document
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument();

        // Create SOAP Envelope
        Element envelope = document.createElement("soap:Envelope");
        envelope.setAttribute("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
        document.appendChild(envelope);

        // Create SOAP Body
        Element body = document.createElement("soap:Body");
        envelope.appendChild(body);

        // Create your specific XML structure based on the JSON data
        Element inqData = document.createElement("inqdata");
        inqData.setAttribute("xmlns", "http://inqdata.wsbeans.iseries/");
        body.appendChild(inqData);

        Element arg0 = document.createElement("arg0");
        inqData.appendChild(arg0);

        // Iterate over the fields of the object and create XML elements
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = getFieldName(field); // Get the element name from the annotation
            Object fieldValue = field.get(object);

            if (fieldValue != null) {
                Element fieldElement = document.createElement(fieldName);
                Text fieldText = document.createTextNode(fieldValue.toString());
                fieldElement.appendChild(fieldText);
                arg0.appendChild(fieldElement);
            }

            field.setAccessible(false);
        }

        // Convert the DOM Document to a String
        StringWriter stringWriter = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(
                new DOMSource(document),  new StreamResult(stringWriter));

        return stringWriter.toString();
    }

    public static <T> T parseXML(String requestXML, Class<T> clazz) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(requestXML)));

        T obj = clazz.getDeclaredConstructor().newInstance();
        Element returnElement = (Element) document.getElementsByTagName("return").item(0);

        NodeList fieldNodes = returnElement.getChildNodes();
        for (int i = 0; i < fieldNodes.getLength(); i++) {
            Node fieldNode = fieldNodes.item(i);
            if (fieldNode instanceof Element) {
                Element fieldElement = (Element) fieldNode;
                String fieldName = fieldElement.getTagName();
                String fieldValue = fieldElement.getTextContent();

                setFieldValue(obj, fieldName, fieldValue);
            }
        }

        return obj;
    }

    private static String getFieldName(Field field) {
        XmlNative annotation = field.getAnnotation(XmlNative.class);
        if (annotation != null) {
            return annotation.name();
        }
        return field.getName();
    }

    private static <T> void setFieldValue(T object, String fieldName, String fieldValue) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            XmlNative xmlField = field.getAnnotation(XmlNative.class);
            if (xmlField != null && xmlField.name().equals(fieldName)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object parsedValue = parseValue(fieldType, fieldValue);
                field.set(object, parsedValue);
                field.setAccessible(false);
                break;
            }
        }
    }

    private static Object parseValue(Class<?> fieldType, String fieldValue) {
        if (fieldType == String.class) {
            return fieldValue;
        } else if (fieldType == Integer.class) {
            return Integer.parseInt(fieldValue);
        } else if (fieldType == Double.class) {
            return Double.parseDouble(fieldValue);
        } else if (fieldType == Boolean.class) {
            return Boolean.parseBoolean(fieldValue);
        }

        throw new IllegalArgumentException(String.format("Unsupported field type: %s", fieldValue));
    }
}
