package com.sial.bluematerials;

import org.json.JSONException;
import org.json.JSONObject;

public class Material {

    private int itemID;
    private String itemNumber;
    private String itemDescirption;
//            "itemNumber": "1410600000",
//            "descriptionEn": "MIRAVAL 95311 WR SCENIC WHITE",
//            "descriptionDe": "Miraval 95311 Scenic White WR",
//            "descriptionCommon": "95311",
//            "inactive": 1,
//            "modification": "2019-12-03T13:54:02.000+0000",
//            "itemColorId": 1,
//            "itemColorCode": "SI",
//            "itemColorName": "Silver",
//            "itemTypeId": 1,
//            "itemTypeCode": "M",
//            "itemTypeName": "Mica calcined",
//            "itemGroupId": 2,
//            "itemGroupCode": "2",
//            "itemGroupName": "9000 Type WR",
//            "itemFractionId": null,
//            "itemFractionCode": null,
//            "itemFractionName": null

    public static Material parseMaterial(JSONObject jsonObject)
    {
        Material newMaterial = null;
        try {
            int itemID = jsonObject.getInt("itemID");
            if(itemID<=0)
            {
                return  newMaterial;
            }

            newMaterial = new Material();
            newMaterial.setItemID(itemID);
            newMaterial.setItemNumber(jsonObject.getString("itemNumber"));
            newMaterial.setItemDescirption(jsonObject.getString("descriptionCommon"));
            return newMaterial;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newMaterial;
    }

    public int getItemID() {
        return itemID;
    }

    private void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDescirption() {
        return itemDescirption;
    }

    public void setItemDescirption(String itemDescirption) {
        this.itemDescirption = itemDescirption;
    }
}


