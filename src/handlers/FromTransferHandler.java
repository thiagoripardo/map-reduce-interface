package handlers;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class FromTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;

	public int getSourceActions(JComponent comp) {
        return COPY_OR_MOVE;
    }

    private int index = 0;
    //https://gist.github.com/edpichler/840867#file-jlabelanimal-java-L4
    /*public Transferable createTransferable(JComponent comp) {
        index = dragFrom.getSelectedIndex();
        if (index < 0 || index >= from.getSize()) {
            return null;
        }

        return new StringSelection((String)dragFrom.getSelectedValue());
    }
     
    public void exportDone(JComponent comp, Transferable trans, int action) {
        if (action != MOVE) {
            return;
        }

        from.removeElementAt(index);
    }*/
}
