package com.partgah.behravesh.Models.Listners;

import com.partgah.behravesh.Models.locationModel;

public interface locationLisitner {
    public void OnFound(locationModel location);
    public void OnError(String ErrorMessage);

}
