package com.phincon.wls.model.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@XmlRootElement(name = "return")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private String acctNbr;
    private String acctType;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String cif;
    private String ctcOther;
    private String dob;
    private String faxNbr;
    private String fullName;
    private String homePh;
    private String identity;
    private String keyParam;
    private String maidenName;
    private String mobilePh;
    private String npwp;
    private String officePh;
    private String pob;
    private String priority;
    private String resp;
    private String zipCode;

    @XmlElement(name = "ACCTNBR")
    public String getAcctNbr() {
        return acctNbr;
    }

    public void setAcctNbr(String acctNbr) {
        this.acctNbr = acctNbr;
    }

    @XmlElement(name = "ACCTTYPE")
    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    @XmlElement(name = "ADDRESS1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @XmlElement(name = "ADDRESS2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @XmlElement(name = "ADDRESS3")
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @XmlElement(name = "ADDRESS4")
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @XmlElement(name = "ADDRESS5")
    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    @XmlElement(name = "CIF")
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @XmlElement(name = "CTCOTHER")
    public String getCtcOther() {
        return ctcOther;
    }

    public void setCtcOther(String ctcOther) {
        this.ctcOther = ctcOther;
    }

    @XmlElement(name = "DOB")
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @XmlElement(name = "FAXNBR")
    public String getFaxNbr() {
        return faxNbr;
    }

    public void setFaxNbr(String faxNbr) {
        this.faxNbr = faxNbr;
    }

    @XmlElement(name = "FULLNAME")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement(name = "HOMEPH")
    public String getHomePh() {
        return homePh;
    }

    public void setHomePh(String homePh) {
        this.homePh = homePh;
    }

    @XmlElement(name = "IDENTITY")
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @XmlElement(name = "KEYPARAM")
    public String getKeyParam() {
        return keyParam;
    }

    public void setKeyParam(String keyParam) {
        this.keyParam = keyParam;
    }

    @XmlElement(name = "MAIDENNAME")
    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    @XmlElement(name = "MOBILEPH")
    public String getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(String mobilePh) {
        this.mobilePh = mobilePh;
    }

    @XmlElement(name = "NPWP")
    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    @XmlElement(name = "OFFICEPH")
    public String getOfficePh() {
        return officePh;
    }

    public void setOfficePh(String officePh) {
        this.officePh = officePh;
    }

    @XmlElement(name = "POB")
    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    @XmlElement(name = "PRIORITY")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @XmlElement(name = "RESP")
    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    @XmlElement(name = "ZIPCODE")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
