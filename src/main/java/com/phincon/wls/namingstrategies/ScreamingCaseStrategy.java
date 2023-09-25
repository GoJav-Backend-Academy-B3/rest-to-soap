package com.phincon.wls.namingstrategies;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class ScreamingCaseStrategy extends PropertyNamingStrategies.NamingBase {

    @Override
    public String translate(String arg0) {
        return arg0.chars().mapToObj(Character::toUpperCase)
                .map(String::valueOf).collect(Collectors.joining());
    }

}
