package com.sub.controller.DAO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class ReqBody {
    private List<String> account;
    private String localDate;
}
