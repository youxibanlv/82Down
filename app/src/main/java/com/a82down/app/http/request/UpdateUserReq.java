package com.a82down.app.http.request;

import com.a82down.app.db.table.User;
import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/7/7.
 */
public class UpdateUserReq extends BaseRequest {
    RequestParam requestParams;

    public UpdateUserReq(User user){
        cmdType = "userService";
        methodName = "updateUser";
        requestParams = new RequestParam(user);
    }

    class RequestParam{
        User user;
        public RequestParam(User user){
            this.user = user;
        }
    }
}
