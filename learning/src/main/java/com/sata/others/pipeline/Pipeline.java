package com.sata.others.pipeline;

public interface Pipeline<T> {

    void process(PipelineContext ctx, T t);

    void forward(PipelineContext ctx, T t);
}