package org.ditto.datapulse.conntector;

public interface BatchConnector<RequestContextT, ResultT> extends SourceConnector {
    ResultT execute(RequestContextT cxt);
}
