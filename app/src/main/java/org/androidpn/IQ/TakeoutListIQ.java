package org.androidpn.IQ;

import org.androidpn.model.TakeoutOrder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class TakeoutListIQ extends IQ {

    private List<TakeoutOrder> takeoutOrderList;

    public TakeoutListIQ() {
    }

    public List<TakeoutOrder> getTakeoutOrderList() {
        return takeoutOrderList;
    }

    public void setTakeoutOrderList(List<TakeoutOrder> takeoutOrderList) {
        this.takeoutOrderList = takeoutOrderList;
    }

    @Override
    public String getChildElementXML() {
        return null;
    }
}
