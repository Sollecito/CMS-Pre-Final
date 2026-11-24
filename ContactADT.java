
public interface ContactADT {

    int     getSize();
    Contact get(int index);
    void    insertAtEnd(Contact c);
    boolean insertAtPosition(int index, Contact c);
    boolean delete(int index);
}
