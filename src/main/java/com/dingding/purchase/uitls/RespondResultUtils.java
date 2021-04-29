package com.dingding.purchase.uitls;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Setter
@Getter
@AllArgsConstructor
public class RespondResultUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    private Integer status;
    private String msg;
    private Object data;

    @SneakyThrows
    public static String ok() {
        return mapper.writeValueAsString(new RespondResultUtils(200, "成功", null));
    }

    @SneakyThrows
    public static String errorMsg(String msg) {
        return mapper.writeValueAsString(new RespondResultUtils(500, msg, null));
    }

    @SneakyThrows
    public static String ok(Object data){
        return mapper.writeValueAsString(new RespondResultUtils(200,"成功",data));
    }
}