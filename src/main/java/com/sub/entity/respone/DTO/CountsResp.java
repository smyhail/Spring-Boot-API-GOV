package com.sub.entity.respone.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountsResp {
    private  long checked;
    private long ok;
    private long wrong;
    private long bad;
}
