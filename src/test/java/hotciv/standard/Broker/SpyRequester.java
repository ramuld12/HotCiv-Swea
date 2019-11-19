package hotciv.standard.Broker;
import frds.broker.Requestor;

import java.lang.reflect.Type;

public class SpyRequester implements Requestor {
  public String lastObjectId;
  public String lastOperationName;
  public Object[] lastArgument;
  public Type lastType;

  @Override
  public <T> T sendRequestAndAwaitReply(String objectId, String operationName, Type typeOfReturnValue, Object... argument) {
    lastObjectId = objectId;
    lastOperationName = operationName;
    lastArgument = argument;
    lastType = typeOfReturnValue;
    return null;
  }

  @Override
  public void close() {

  }
}
