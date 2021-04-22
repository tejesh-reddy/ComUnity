package com.example.ComUnity.Domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Access {

    private boolean post_access = false;
    private boolean join_access = true;
    private boolean sheriff = false;
}
