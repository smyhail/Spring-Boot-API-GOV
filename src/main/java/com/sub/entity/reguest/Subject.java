
package com.sub.entity.reguest;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "nip",
    "statusVat",
    "regon",
    "pesel",
    "krs",
    "residenceAddress",
    "workingAddress",
    "representatives",
    "authorizedClerks",
    "partners",
    "registrationLegalDate",
    "registrationDenialBasis",
    "registrationDenialDate",
    "restorationBasis",
    "restorationDate",
    "removalBasis",
    "removalDate",
    "accountNumbers",
    "hasVirtualAccounts"
})
@Generated("jsonschema2pojo")
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
public class Subject {

    @JsonProperty("name")
    private String name;
    @JsonProperty("nip")
    private String nip;
    @JsonProperty("statusVat")
    private String statusVat;
    @JsonProperty("regon")
    private String regon;
    @JsonProperty("pesel")
    private String pesel;
    @JsonProperty("krs")
    private String krs;
    @JsonProperty("residenceAddress")
    private String residenceAddress;
    @JsonProperty("workingAddress")
    private String workingAddress;
    @JsonProperty("representatives")
    private List<Object> representatives = null;
    @JsonProperty("authorizedClerks")
    private List<Object> authorizedClerks = null;
    @JsonProperty("partners")
    private List<Object> partners = null;
    @JsonProperty("registrationLegalDate")
    private String registrationLegalDate;
    @JsonProperty("registrationDenialBasis")
    private Object registrationDenialBasis;
    @JsonProperty("registrationDenialDate")
    private Object registrationDenialDate;
    @JsonProperty("restorationBasis")
    private Object restorationBasis;
    @JsonProperty("restorationDate")
    private Object restorationDate;
    @JsonProperty("removalBasis")
    private Object removalBasis;
    @JsonProperty("removalDate")
    private Object removalDate;
    @JsonProperty("accountNumbers")
    private List<String> accountNumbers = null;
    @JsonProperty("hasVirtualAccounts")
    private Boolean hasVirtualAccounts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nip")
    public String getNip() {
        return nip;
    }

    @JsonProperty("nip")
    public void setNip(String nip) {
        this.nip = nip;
    }

    @JsonProperty("statusVat")
    public String getStatusVat() {
        return statusVat;
    }

    @JsonProperty("statusVat")
    public void setStatusVat(String statusVat) {
        this.statusVat = statusVat;
    }

    @JsonProperty("regon")
    public String getRegon() {
        return regon;
    }

    @JsonProperty("regon")
    public void setRegon(String regon) {
        this.regon = regon;
    }

    @JsonProperty("pesel")
    public String getPesel() {
        return pesel;
    }

    @JsonProperty("pesel")
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @JsonProperty("krs")
    public String getKrs() {
        return krs;
    }

    @JsonProperty("krs")
    public void setKrs(String krs) {
        this.krs = krs;
    }

    @JsonProperty("residenceAddress")
    public String getResidenceAddress() {
        return residenceAddress;
    }

    @JsonProperty("residenceAddress")
    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    @JsonProperty("workingAddress")
    public String getWorkingAddress() {
        return workingAddress;
    }

    @JsonProperty("workingAddress")
    public void setWorkingAddress(String workingAddress) {
        this.workingAddress = workingAddress;
    }

    @JsonProperty("representatives")
    public List<Object> getRepresentatives() {
        return representatives;
    }

    @JsonProperty("representatives")
    public void setRepresentatives(List<Object> representatives) {
        this.representatives = representatives;
    }

    @JsonProperty("authorizedClerks")
    public List<Object> getAuthorizedClerks() {
        return authorizedClerks;
    }

    @JsonProperty("authorizedClerks")
    public void setAuthorizedClerks(List<Object> authorizedClerks) {
        this.authorizedClerks = authorizedClerks;
    }

    @JsonProperty("partners")
    public List<Object> getPartners() {
        return partners;
    }

    @JsonProperty("partners")
    public void setPartners(List<Object> partners) {
        this.partners = partners;
    }

    @JsonProperty("registrationLegalDate")
    public String getRegistrationLegalDate() {
        return registrationLegalDate;
    }

    @JsonProperty("registrationLegalDate")
    public void setRegistrationLegalDate(String registrationLegalDate) {
        this.registrationLegalDate = registrationLegalDate;
    }

    @JsonProperty("registrationDenialBasis")
    public Object getRegistrationDenialBasis() {
        return registrationDenialBasis;
    }

    @JsonProperty("registrationDenialBasis")
    public void setRegistrationDenialBasis(Object registrationDenialBasis) {
        this.registrationDenialBasis = registrationDenialBasis;
    }

    @JsonProperty("registrationDenialDate")
    public Object getRegistrationDenialDate() {
        return registrationDenialDate;
    }

    @JsonProperty("registrationDenialDate")
    public void setRegistrationDenialDate(Object registrationDenialDate) {
        this.registrationDenialDate = registrationDenialDate;
    }

    @JsonProperty("restorationBasis")
    public Object getRestorationBasis() {
        return restorationBasis;
    }

    @JsonProperty("restorationBasis")
    public void setRestorationBasis(Object restorationBasis) {
        this.restorationBasis = restorationBasis;
    }

    @JsonProperty("restorationDate")
    public Object getRestorationDate() {
        return restorationDate;
    }

    @JsonProperty("restorationDate")
    public void setRestorationDate(Object restorationDate) {
        this.restorationDate = restorationDate;
    }

    @JsonProperty("removalBasis")
    public Object getRemovalBasis() {
        return removalBasis;
    }

    @JsonProperty("removalBasis")
    public void setRemovalBasis(Object removalBasis) {
        this.removalBasis = removalBasis;
    }

    @JsonProperty("removalDate")
    public Object getRemovalDate() {
        return removalDate;
    }

    @JsonProperty("removalDate")
    public void setRemovalDate(Object removalDate) {
        this.removalDate = removalDate;
    }

    @JsonProperty("accountNumbers")
    public List<String> getAccountNumbers() {
        return accountNumbers;
    }

    @JsonProperty("accountNumbers")
    public void setAccountNumbers(List<String> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    @JsonProperty("hasVirtualAccounts")
    public Boolean getHasVirtualAccounts() {
        return hasVirtualAccounts;
    }

    @JsonProperty("hasVirtualAccounts")
    public void setHasVirtualAccounts(Boolean hasVirtualAccounts) {
        this.hasVirtualAccounts = hasVirtualAccounts;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    */

}
