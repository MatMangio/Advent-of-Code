/**
 * OVERVIEW: exception class to notify not enough input was passed in an iterator.
 */
public class NotEnoughInputInIterator extends RuntimeException {
    public NotEnoughInputInIterator() {super();}
    public NotEnoughInputInIterator(String msg) {super(msg);}
}
