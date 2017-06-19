package com.fererlab.undertow.app;

import com.fererlab.undertow.dbc.annotation.Contracted;
import com.fererlab.undertow.dbc.annotation.Ensures;
import com.fererlab.undertow.dbc.annotation.Invariant;
import com.fererlab.undertow.dbc.annotation.Requires;
import com.fererlab.undertow.dbc.interceptor.DBCInterceptor;

import javax.interceptor.Interceptors;

@Interceptors({DBCInterceptor.class})
@Contracted
@Invariant({
  "bean.getName() != null"
})
public class InjectBean {

    public String getName() {
        return InjectBean.class.getSimpleName();
    }

    @Requires({
      "params[0]!=null",
      "params[0]!=\"\""
    })
    @Ensures({
      "result!=null",
      "result==\"HI \" + params[0]"
    })
    public String sayHi(String name) {
        return "HI " + name;
    }

}
