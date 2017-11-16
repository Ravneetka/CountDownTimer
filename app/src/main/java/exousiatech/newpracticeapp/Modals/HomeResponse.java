package exousiatech.newpracticeapp.Modals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30-10-2017.
 */

public class HomeResponse {

    int status;
    List<BlogResponseData> response=new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BlogResponseData> getResponse() {
        return response;
    }

    public void setResponse(List<BlogResponseData> response) {
        this.response = response;
    }
}
