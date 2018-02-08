package org.androidpn.IQ;

import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

/**
 * Created by pro1 on 18/2/6.
 */

public class ActivityInquiryIQ extends IQ {
    List<Bussiness> bussinessList;

    @Override
    public String getChildElementXML() {
        return null;
    }

    public List<Bussiness> getBussinessList() {
        return bussinessList;
    }

    public void setBussinessList(List<Bussiness> bussinessList) {
        this.bussinessList = bussinessList;
    }
}
