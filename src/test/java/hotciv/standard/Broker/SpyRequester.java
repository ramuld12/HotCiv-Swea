package hotciv.standard.Broker;
import frds.broker.Requestor;

import java.lang.reflect.Type;

public class SpyRequester implements Requestor {
  String lastObjectId;
  String lastOperationName;
  Object[] lastArgument;
  Type lastType;

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
