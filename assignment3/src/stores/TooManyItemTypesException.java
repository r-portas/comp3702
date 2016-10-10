/**
 * Exception to be thrown when the store has too many item types
 */
package stores;

import java.lang.Exception;

public class TooManyItemTypesException extends Exception {
    public TooManyItemTypesException() {
        super();
    }

    public TooManyItemTypesException(String msg) {
        super(msg);
    }
}
