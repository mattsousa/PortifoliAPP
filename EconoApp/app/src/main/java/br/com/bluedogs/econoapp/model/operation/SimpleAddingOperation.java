package br.com.bluedogs.econoapp.model.operation;

/**
 * Created by Sarah Francis on 12/01/2017.
 */

public class SimpleAddingOperation implements OperationType {
    @Override
    public char getOperationType() {
        return 'a';
    }
}
