package hotciv.standard.Broker;
import frds.broker.ClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;

import java.lang.reflect.Type;

public class SpyRequester extends StandardJSONRequestor {
  private String lastObjectId;
  private String lastOperationName;
  private Object[] lastArguments;
  private Type lastType;

  public SpyRequester(ClientRequestHandler crh) {
    super(crh);
  }

  @Override
  public <T> T sendRequestAndAwaitReply(String objectId, String operationName, Type typeOfReturnValue, Object... argument) {
    this.lastObjectId = objectId;
    this.lastOperationName = operationName;
    this.lastArguments = argument;
    this.lastType = typeOfReturnValue;
    return super.sendRequestAndAwaitReply(objectId, operationName, typeOfReturnValue, argument);
  }

  public String  getLastObjectId() {
    return lastObjectId;
  }

  public String  getLastOperationName() {
    return lastOperationName;
  }

  public Object[] getLastArguments() {
    return lastArguments;
  }

  public Type getLastType() {
    return lastType;
  }

  @Override
  public void close() {

  }
}
