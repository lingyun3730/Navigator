package com.sata.others.pipeline;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PipelineContext {
    //some fields here
    private String printName = "";

    public void addPrintName (String name) {
        printName += name;
    }
}
