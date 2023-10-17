package com.iloveyou.util;

import com.iloveyou.entity.Animal;
import lombok.Data;

@Data
public class AnimalParentRequest {
    private Animal child;
    private Animal father;
    private Animal mother;
}
