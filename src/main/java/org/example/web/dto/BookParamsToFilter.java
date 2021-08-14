package org.example.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Created by Andrey.Yamangulov
 * Date: 08.08.2021
 * Time: 11:49
 */
public class BookParamsToFilter {
    @Size(max = 60)
    private String author;

    @Size(max = 100)
    private String title;

    @Digits(integer = 4, fraction = 0)
    private Integer minSize;

    @Digits(integer = 4, fraction = 0)
    private Integer maxSize;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
}
