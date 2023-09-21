package com.phincon.wls.utils;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class CustomNamespacePrefixMapper extends NamespacePrefixMapper {
    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if ("http://schemas.xmlsoap.org/soap/envelope/".equals(namespaceUri)) {
            return "soap";
        } else if ("http://inqdata.wsbeans.iseries/".equals(namespaceUri)) {
            return "ns2";
        }
        return suggestion;
    }
}
