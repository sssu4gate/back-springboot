package com.gate.planner.gate;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = makeStore(context);
        Long startTime = (Long) store.remove("startTime");
        System.out.println("Total TestExecutionTime :"+ (System.currentTimeMillis()-startTime));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = makeStore(context);
        store.put("startTime",System.currentTimeMillis());
    }

    private ExtensionContext.Store makeStore(ExtensionContext context)
    {
        String className =context.getRequiredTestClass().getName();
        String methodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(className, methodName));
    }
}
