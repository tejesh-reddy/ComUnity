package com.example.ComUnity.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostForm {


    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Have to have atleast 10 letters of content")
    @Size(min = 10, max = 256, message = "Content can't exceed 257 characters or be below 10")
    private String content;

}
