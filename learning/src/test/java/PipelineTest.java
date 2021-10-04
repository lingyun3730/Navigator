import com.sata.others.pipeline.*;
import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;

public class PipelineTest {

    private static final class F extends ForwardingPipeline<String> {

        public F(@NonNull String name, @NonNull Pipeline<? super String> next) {
            super(name, next);
        }

        @Override
        public void process(PipelineContext ctx, String s) {
            ctx.addPrintName(this.getName());
            forward(ctx, s);
        }
    }

    private static final class E extends EndPipeline<String> {

        public E(String name) {
            super(name);
        }

        @Override
        public void process(PipelineContext ctx, String s) {
            ctx.addPrintName(this.getName());
            forward(ctx, s);
        }
    }

    @Test
    public void testFunctional() {

        Pipeline<String> p1 = new FunctionalPipelineBuilder<String, Pipeline<String>>().end(new E("1"));
        PipelineContext pipelineContext1 = new PipelineContext();
        p1.process(pipelineContext1, p1.toString());
        Assert.assertEquals("1", pipelineContext1.getPrintName());

        Pipeline<String> pipeline2 =
                new FunctionalPipelineBuilder<String, Pipeline<String>>()
                        .add(next -> new F("1", next))
                        .add(next -> new F("2", next))
                        .end(new E("3"));
        PipelineContext pipelineContext = new PipelineContext();
        pipeline2.process(pipelineContext, pipeline2.toString());
        Assert.assertEquals("123", pipelineContext.getPrintName());
    }
    @Test
    public void testStack() {
        Pipeline<String> pipeline =
                new StackPipelineBuilder<String>()
                        .add(next -> new F("1", next))
                        .add(next -> new F("2", next))
                        .end(new E("3"));
        PipelineContext pipelineContext = new PipelineContext();
        pipeline.process(pipelineContext, pipeline.toString());
        Assert.assertEquals("123", pipelineContext.getPrintName());
    }
}
