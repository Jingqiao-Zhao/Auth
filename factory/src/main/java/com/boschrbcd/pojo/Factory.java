package com.boschrbcd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factory {
    private Integer id;
    private String name;
    private String location;
}
