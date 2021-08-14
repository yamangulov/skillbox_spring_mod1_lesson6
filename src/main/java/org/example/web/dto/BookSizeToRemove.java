package org.example.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrey.Yamangulov
 * Date: 06.08.2021
 * Time: 14:06
 */
public class BookSizeToRemove {

    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
