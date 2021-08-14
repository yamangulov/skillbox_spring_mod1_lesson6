package org.example.web.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Andrey.Yamangulov
 * Date: 14.08.2021
 * Time: 09:11
 */
public class UserIdToRemove {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
