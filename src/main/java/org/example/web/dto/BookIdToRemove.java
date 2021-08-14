package org.example.web.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Andrey.Yamangulov
 * Date: 06.08.2021
 * Time: 14:06
 */
public class BookIdToRemove {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
