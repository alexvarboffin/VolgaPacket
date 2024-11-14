package com.wardrumstudios.utils;

import ru.volga.utils.DLog;

public class WarBilling extends WarBase {
    public native void notifyChange(String str, int i);
    public native void changeConnection(boolean z);

    public void AddSKU(String str)
    {
        DLog.d("**** AddSKU: " + str);
    }

    public boolean InitBilling()
    {
        DLog.d("**** InitBilling()");
        return true;
    }

    public String LocalizedPrice(String str) {
        DLog.d("**** LocalizedPrice: " + str);
        return "";
    }

    public boolean RequestPurchase(String str)
    {
        DLog.d("**** RequestPurchase: " + str);
        return true;
    }

    public void SetBillingKey(String str)
    {
        DLog.d("**** SetBillingKey: " + str);
    }
}
