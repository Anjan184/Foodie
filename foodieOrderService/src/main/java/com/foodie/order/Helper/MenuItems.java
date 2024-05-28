package com.foodie.order.Helper;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Embeddable
@Data
public class MenuItems {

    private String menuName;
    private String menuPrice;
}

