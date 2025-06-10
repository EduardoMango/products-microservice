package com.EduardoMango.products.microservice.util;

import java.math.BigDecimal;

public class ConverterUtil {

public static BigDecimal stringToBigDecimal(String str){
    if (str == null) return null;
    return new BigDecimal(str);
}
}
