package br.com.bluedogs.econoapp.model.operation;

/**
 * Created by Sarah Francis on 12/01/2017.
 */

public class SimpleRemovingOperation implements OperationType {
    @Override
    public char getOperationType() {
        return 'r';
    }
}
