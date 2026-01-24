package org.ditto.datapulse.strategy.modifier;

public interface BatchResultModifier<ResultT> {
    void refine(ResultT result);
}
