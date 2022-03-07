package com.sub.entity.respone;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Response{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Long id;

    @Column(name = "checkedOnDate")
    private String checkedOnDate;

    @Column(name="numberChecked")
    private String numberChecked;

    @Column(name = "name")
    private String name;

    @Column(name = "nip")
    private String nip;
    @Column(name = "statusVat")
    private String statusVat;
    @Column(name = "regon")
    private String regon;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "krs")
    private String krs;


    @Column(name = "residenceAddress")
    private String residenceAddress;
    @Column(name = "workingAddress")
    private String workingAddress;

    @Column(name = "representatives")
    private String representatives;
    @Column(name = "authorizedClerks")
    private String authorizedClerks;
    @Column(name = "partners")
    private String partners;

    @Column(name = "registrationLegalDate")
    private String registrationLegalDate;
    @Column(name = "registrationDenialBasis")
    private String registrationDenialBasis;
    @Column(name = "registrationDenialDate")
    private String registrationDenialDate;

    @Column(name = "restorationBasis")
    private String restorationBasis;
    @Column(name = "restorationDate")
    private String restorationDate;
    @Column(name = "removalBasis")

    private String removalBasis;
    @Column(name = "removalDate")
    private String removalDate;


    @ElementCollection(targetClass=String.class)
    @Column(name = "accountNumbers")
    private List<String> accountNumbers;


    @Column(name = "message")
    private String message;
    @Column(name = "code")
    private String code;
    @Column(name = "hasVirtualAccounts")
    private Boolean hasVirtualAccounts;
    @Column(name = "requestDateTime")
    private String requestDateTime;
    @Column(name = "requestId")
    private String requestId;


}
