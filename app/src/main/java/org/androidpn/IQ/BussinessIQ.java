package org.androidpn.IQ;

import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/2/8.
 */

public class BussinessIQ extends IQ {

    private Bussiness bussiness;

    public BussinessIQ() {

    }

    public Bussiness getBussiness() {
        return bussiness;
    }

    public void setBussiness(Bussiness bussiness) {
        this.bussiness = bussiness;
    }

    @Override
    public String getChildElementXML() {
        return null;
    }
}
