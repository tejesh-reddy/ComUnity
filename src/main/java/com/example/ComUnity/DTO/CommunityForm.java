package com.example.ComUnity.DTO;

import com.example.ComUnity.Domain.Community;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommunityForm {

    @NotBlank(message = "Required Field")
    @Size(min = 4, max= 20, message = "Must be between 4 and 20 characters")
    private String name;

    @NotBlank
    @Size(min = 10, max = 40, message = "Must be between 10 and 40 characters")
    private String description;

}
