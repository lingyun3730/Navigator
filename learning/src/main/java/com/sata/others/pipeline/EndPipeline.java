package com.sata.others.pipeline;

import lombok.Getter;

@Getter
public abstract class EndPipeline<T> implements Pipeline<T> {

    private final String name;

    public EndPipeline(String name) {
        this.name = name;
    }

    @Override
    public abstract void process(PipelineContext ctx, T t);

    @Override
    public final void forward(PipelineContext ctx, T t) {
        //do nothing;
    }

    @Override
    public String toString() {
        return name;
    }
}
